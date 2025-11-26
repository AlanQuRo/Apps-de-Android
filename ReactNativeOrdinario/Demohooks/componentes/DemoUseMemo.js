import React,{useMemo} from "react";
import { StyleSheet,Text } from "react-native";

const notas=[{asignatura:'moviles',calif:10},
    {asignatura:'proyectos',calif:9},
    {asignatura:'SOA',calif:8}]

export default ()=>{

    const promedio=useMemo(
        ()=>{
            let promedio=0
            console.log("calculando promedio...")
            notas.forEach(x=>{
                promedio=promedio+x.calif
            })
            return promedio/notas.length
        },[notas]
    );
    
    return(
        <Text style={style.texto}>
            {promedio}

        </Text>
    )
}

const style = StyleSheet.create({
    texto:{
        fontSize:48,
},
});

