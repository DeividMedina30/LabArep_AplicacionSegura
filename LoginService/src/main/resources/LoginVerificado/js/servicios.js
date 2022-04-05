var obtenerServicio = (function () {
    console.log("Entro 1")
    function obtenerFecha(){
        console.log("Entro ne dos");
        axios.get("/LoginVerificado/servicio").then(res=>{
            document.getElementById("servicio").innerHTML = "La fecha y hora actual es: "+res.data;
        })
    }
    return {
        obtenerFecha:obtenerFecha
    };
})();