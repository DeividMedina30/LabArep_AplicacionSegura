var obtenerServicio = (function () {
    function obtenerFecha(){
        axios.get("/LoginVerificado/servicio").then(res=>{
            document.getElementById("servicio").innerHTML = "La fecha y hora actual es: "+res.data;
        })
    }
    function salir(){
        axios.post("/cerrarSeccion").then(res=>{
            if(res.data==="Se cerro correctamente la sección") {
                window.location.href = '/';
            }
        })
    }
    function obtenerUsuario(){
        axios.get("/LoginVerificado/getNameUser").then(res=>{
            document.getElementById("nombreUser").innerHTML = res.data;
        })
    }
    return {
        obtenerFecha:obtenerFecha,
        salir:salir,
        obtenerUsuario:obtenerUsuario
    };
})();