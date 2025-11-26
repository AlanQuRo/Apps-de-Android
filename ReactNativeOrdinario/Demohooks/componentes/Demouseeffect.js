import React,{useState, useEffect} from "react";
import { StyleSheet,Text } from "react-native";

export default ()=>{

    const[loading, setLoading] = useState(true)
    useEffect(()=>{
        setTimeout(()=>{
            setLoading(false)
        },2000)
    })
    return(
        <Text style={style.texto}>
            {loading ? "Cargando....": "listo"}

        </Text>
    )
}

const style = StyleSheet.create({
    texto:{
        fontSize:48,
},
});

