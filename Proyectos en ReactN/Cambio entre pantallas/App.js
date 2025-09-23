import * as React from 'react';
import { Button, View, Text } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

function HomeScreen({ navigation }) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Pantalla Home</Text>
      <Button
        title="Ir a Detalles"
        onPress={() => navigation.navigate('Detalles')}
      />
      <Button
        title="Ir a Perfil"
        onPress={() => navigation.navigate('Perfil')}
      />
    </View>
  );
}

function DetailsScreen({ navigation }) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Pantalla Detalles</Text>
      <Button title="Regresar a Home" onPress={() => navigation.navigate('Home')} />
    </View>
  );
}

function ProfileScreen({ navigation }) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Pantalla Perfil</Text>
      <Button title="Regresar a Home" onPress={() => navigation.navigate('Home')} />
    </View>
  );
}

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Detalles" component={DetailsScreen} />
        <Stack.Screen name="Perfil" component={ProfileScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
