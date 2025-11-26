import React,{useState} from "react";
import { StyleSheet,Text } from "react-native";

export default ()=>{

    const[contador, setContador] = useState(0)
    return(
        <>
            <Text style={style.texto}
                onPress={()=>setContador(contador+1)}>+</Text>
            <Text style={style.texto}>{contador}</Text>
            <Text style={style.texto}
                onPress={()=>setContador(contador-1)}>-</Text>
                </>
    )
}

const style = StyleSheet.create({
    texto:{
        fontSize:48,
},
});

