var Servicios = (function () {

    var url="https://localhost:35000";
    function login(usermameid,userPasswordid){
        const user={usuario:usermameid,password:userPasswordid} //Creando Formato JSon, El nombre tiene que ser igual que en el Usuario
        axios.post("/login",user).then(res=>{ //Llamar la instancia Login para validar datos
            console.log(res.data)
            if(res.data !== ' '){
                window.location.href= 'LoginVerificado/UsuarioLogin.html';
            }
            else {
                alert("El usuario o clave esta incorrect@, vuelva a intentar");
            }
        })
    }

    return {
        login:login,
    };
})();