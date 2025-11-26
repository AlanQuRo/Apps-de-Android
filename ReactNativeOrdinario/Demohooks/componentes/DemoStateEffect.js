import React,{useState, useEffect} from "react";
import { StyleSheet,Text } from "react-native";

export default ()=>{

    const[loading, setLoading] = useState(true)
    const[usuarios,setUsuarios] = useState([])
   
   // FORMA INCORRECTA
    // useEffect(async()=>{
     //   const response=await fetch('https://jsonplaceholder.typicode.com/users')
       // const json=await response.json()
        //setUsuarios(json)
        //setLoading(false)
       // console.log("Cargando datos...")
   // },[])

   // FORMA CORRECTA DE USAR useEffect sin promesas:
   
   const cargarUsuarios= async ()=>{
    const response=await fetch('https://jsonplaceholder.typicode.com/users')
        const json=await response.json()
        setUsuarios(json)
        setLoading(false)
        console.log("Cargando datos...")
   }
   useEffect(()=>{
            cargarUsuarios();
   },[]);
    return(
        <><Text style={style.texto}>
            {loading ? "Cargando....": usuarios[0].name}
        </Text>
        <Text style={style.texto}onPress={()=>{
            setUsuarios([])
            setLoading(true)
        }}>Click me </Text>
        </>
    )
}

const style = StyleSheet.create({
    texto:{
        fontSize:48,
},
});
