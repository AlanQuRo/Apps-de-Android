package com.example.app_parcial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.app.DownloadManager;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AlmacenistaActivity extends AppCompatActivity {

  private DrawerLayout drawerLayout;
  private NavigationView navigationView;

  // ⚙️ Supabase: TUS DATOS
  private static final String SUPABASE_URL = "https://xnsexrumkhttdyefafix.supabase.co";
  private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inhuc2V4cnVta2h0dGR5ZWZhZml4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI5ODQ5MjUsImV4cCI6MjA3ODU2MDkyNX0.jpJI1jQ-ezsyuCQR3XHfncrkGVJHIy3SpXl3pf7vYTg";
  private static final String SUPABASE_BUCKET = "evidencias-almacen";

  private static final int REQUEST_IMAGE_CAPTURE = 1;
  private static final int REQUEST_PICK_IMAGE = 2;

  private static final int REQUEST_CAMERA_PERMISSION = 100;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_almacenista);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawerLayout = findViewById(R.id.drawerLayout);
    navigationView = findViewById(R.id.navigationView);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawerLayout, toolbar,
      R.string.navigation_drawer_open,
      R.string.navigation_drawer_close
    );
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
    TextView tvBienvenida = findViewById(R.id.tvBienvenida);
    tvBienvenida.setText("Bienvenido, " + nombreUsuario + ", manos a la obra!");

    Button btnOpciones1 = findViewById(R.id.btnOpciones1);
    Button btnOpciones2 = findViewById(R.id.btnOpciones2);

    // Botones dentro del recuadro rojo
    Button btnTomarFoto = findViewById(R.id.btnTomarFoto);
    Button btnSubirEvidencia = findViewById(R.id.btnSubirEvidencia);

    // --- Popup de Inventariado ---
    btnOpciones1.setOnClickListener(v -> {
      PopupMenu popup = new PopupMenu(AlmacenistaActivity.this, btnOpciones1);
      popup.getMenuInflater().inflate(R.menu.inventariado, popup.getMenu());

      popup.setOnMenuItemClickListener(item -> {
        int id = item.getItemId();

        if (id == R.id.op1_inventario) {
          // Descargar TXT de inventario
          downloadSupabaseDocument("docs/Inventario_Mes.xlsx", "Inventario_Mes.xlsx");

        } else if (id == R.id.op1_entradas) {
          // Descargar PDF de entradas
          downloadSupabaseDocument("docs/Entradas_Mes.xlsx", "Entradas_Mes.xlsx");

        } else if (id == R.id.op1_salidas) {
          // Descargar PDF de salidas
          downloadSupabaseDocument("docs/Salidas_Mes.xlsx", "Salidas_Mes.xlsx");
        }
        return true;
      });
      popup.show();
    });

    // --- Popup de Reportes ---
    btnOpciones2.setOnClickListener(v -> {
      PopupMenu popup = new PopupMenu(AlmacenistaActivity.this, btnOpciones2);
      popup.getMenuInflater().inflate(R.menu.reportesalm, popup.getMenu());

      popup.setOnMenuItemClickListener(item -> {
        int id = item.getItemId();
        if (id == R.id.op2_diario) {
          Toast.makeText(this, "Reporte Diario seleccionado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op2_mensual) {
          Toast.makeText(this, "Reporte Mensual seleccionado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op2_anual) {
          Toast.makeText(this, "Reporte Anual seleccionado", Toast.LENGTH_SHORT).show();
        }
        return true;
      });
      popup.show();
    });

    // --- Navegación del Drawer ---
    navigationView.setNavigationItemSelectedListener(item -> {
      int id = item.getItemId();

      if (id == R.id.nav_home) {
        // Pantalla actual
      } else if (id == R.id.nav_settings) {
        // Configuraciones (si las implementas)
      } else if (id == R.id.nav_report) {
        Intent intent = new Intent(AlmacenistaActivity.this, reportarerror.class);
        startActivity(intent);
      } else if (id == R.id.nav_logout) {
        Intent intent = new Intent(AlmacenistaActivity.this, seleccionusuario.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
      }

      drawerLayout.closeDrawers();
      return true;
    });

    // --- BOTÓN TOMAR FOTO -> abre cámara (con permiso) ---
    btnTomarFoto.setOnClickListener(v -> {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {

        ActivityCompat.requestPermissions(
          this,
          new String[]{Manifest.permission.CAMERA},
          REQUEST_CAMERA_PERMISSION
        );
      } else {
        openCamera();
      }
    });

    // --- BOTÓN SUBIR EVIDENCIA -> abre galería / archivos (SIN pedir permisos extra) ---
    btnSubirEvidencia.setOnClickListener(v -> {
      openGallery();
    });
  }

  // --- Descargar documento de Supabase ---
  private void downloadSupabaseDocument(String filePath, String fileName) {
    // filePath = ruta dentro del bucket, ej: "docs/Inventario del Mes.txt"
    // fileName = nombre con el que se guardará en Descargas

    String url = SUPABASE_URL
      + "/storage/v1/object/public/"
      + SUPABASE_BUCKET
      + "/"
      + filePath;

    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
    request.setTitle(fileName);
    request.setDescription("Descargando documento...");
    request.setNotificationVisibility(
      DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
    );

    // Carpeta pública de Descargas
    request.setDestinationInExternalPublicDir(
      Environment.DIRECTORY_DOWNLOADS,
      fileName
    );

    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
    if (dm != null) {
      dm.enqueue(request);
      Toast.makeText(this, "Descarga iniciada...", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "No se pudo iniciar la descarga", Toast.LENGTH_SHORT).show();
    }
  }

  // --- Abrir cámara ---
  private void openCamera() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    } else {
      Toast.makeText(this, "No hay aplicación de cámara disponible", Toast.LENGTH_SHORT).show();
    }
  }

  // --- Abrir galería / selector de imágenes ---
  private void openGallery() {
    Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
    pickIntent.setType("image/*");
    startActivityForResult(Intent.createChooser(pickIntent, "Selecciona una imagen"), REQUEST_PICK_IMAGE);
  }

  // --- Resultado de cámara / galería ---
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode != RESULT_OK || data == null) return;

    if (requestCode == REQUEST_IMAGE_CAPTURE) {
      // Foto tomada desde la cámara (miniatura)
      Bundle extras = data.getExtras();
      Bitmap imageBitmap = extras != null ? (Bitmap) extras.get("data") : null;

      // Aquí podrías mostrarla en un ImageView o subirla también a Supabase si quieres
      Toast.makeText(this, "Foto tomada correctamente", Toast.LENGTH_SHORT).show();

    } else if (requestCode == REQUEST_PICK_IMAGE) {
      // Imagen seleccionada de la galería / archivos
      Uri selectedImageUri = data.getData();

      if (selectedImageUri != null) {
        Toast.makeText(this, "Evidencia seleccionada, subiendo...", Toast.LENGTH_SHORT).show();
        uploadImageToSupabase(selectedImageUri);
      }
    }
  }

  // --- Subir imagen a Supabase Storage ---
  private void uploadImageToSupabase(Uri imageUri) {
    // 1. Leer bytes del Uri
    byte[] imageBytes = readBytesFromUri(imageUri);
    if (imageBytes == null) {
      Toast.makeText(this, "No se pudo leer la imagen", Toast.LENGTH_SHORT).show();
      return;
    }

    // 2. Nombre de archivo: por ejemplo, timestamp
    String fileName = "evidencia_" + System.currentTimeMillis() + ".jpg";
    String objectPath = "almacen/" + fileName; // 'almacen' es una carpeta dentro del bucket

    // 3. Cliente HTTP
    OkHttpClient client = new OkHttpClient();

    // 4. Cuerpo de la petición
    RequestBody body = RequestBody.create(
      imageBytes,
      MediaType.parse("image/jpeg")
    );

    // 5. URL de Supabase Storage
    String url = SUPABASE_URL + "/storage/v1/object/" + SUPABASE_BUCKET + "/" + objectPath;

    // 6. Request con headers
    Request request = new Request.Builder()
      .url(url)
      .addHeader("Authorization", "Bearer " + SUPABASE_ANON_KEY)
      .addHeader("apikey", SUPABASE_ANON_KEY)
      .post(body)
      .build();

    // 7. Ejecutar en background
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        runOnUiThread(() ->
          Toast.makeText(AlmacenistaActivity.this,
            "Error al subir evidencia: " + e.getMessage(),
            Toast.LENGTH_LONG).show()
        );
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
          runOnUiThread(() ->
            Toast.makeText(AlmacenistaActivity.this,
              "Evidencia subida correctamente",
              Toast.LENGTH_SHORT).show()
          );
        } else {
          String errorBody = response.body() != null ? response.body().string() : "sin detalles";
          runOnUiThread(() ->
            Toast.makeText(AlmacenistaActivity.this,
              "Error Supabase: " + response.code(),
              Toast.LENGTH_LONG).show()
          );
        }
      }
    });
  }

  // --- Leer bytes desde un Uri ---
  private byte[] readBytesFromUri(Uri uri) {
    try (InputStream inputStream = getContentResolver().openInputStream(uri);
         ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

      if (inputStream == null) return null;

      byte[] data = new byte[4096];
      int nRead;
      while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
      }
      return buffer.toByteArray();

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  // --- Respuesta de permisos ---
  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String[] permissions,
                                         int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (grantResults.length == 0) return;

    if (requestCode == REQUEST_CAMERA_PERMISSION) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "Permiso de cámara concedido, vuelve a presionar el botón.", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "Permiso de cámara denegado.", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
