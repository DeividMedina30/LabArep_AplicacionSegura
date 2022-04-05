var obtenerServicio = (function () {
    function obtenerFecha(){
        axios.get("/LoginVerificado/servicio").then(res=>{
            document.getElementById("servicio").innerHTML = "La fecha y hora actual es: "+res.data;
        })
    }
    function salir(){
        axios.post("/cerrarSeccion").then(res=>{
            if(res.data==="Se cerro correctamente la sección") {
                window.location.href = "/";
            }
        })
    }
    return {
        obtenerFecha:obtenerFecha,
        salir:salir
    };
})();