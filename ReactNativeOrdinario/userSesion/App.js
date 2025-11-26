
import { StyleSheet, Text, View } from 'react-native';
import React ,{useContext}from 'react';
import { AuthContext,AuthProvider } from './moduloApp/AuthContext';


function MainScreen(){
  const {userToken,login,logout} = useContext(AuthContext);
  return(

    <View style={styles.container}>
        <Text onPress={()=>login('token123')}>
          Token del usuario {userToken}
        </Text>
    </View>
  )
}
export default function App() {
  return (
    
      <AuthProvider>
        <MainScreen></MainScreen>
      </AuthProvider>
    
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
