# AREP- ARQUITECTURAS EMPRESARIAL.

## AREP-APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

### INTRODUCCIÓN.

Desarrolle una aplicación Web segura con los siguientes requerimientos:

Debe permitir un acceso seguro desde el browser a la aplicación. Es decir debe garantizar autenticación, autorización e integridad de usuarios.
Debe tener al menos dos computadores comunicacndose entre ellos y el acceso de servicios remotos debe garantizar: autenticación, autorización e integridad entre los servicios. Nadie puede invocar los servicios si no está autorizado.
Explique como escalaría su arquitectura de seguridad para incorporar nuevos servicios.

![introduccion.png](https://i.postimg.cc/mgs2pdwZ/introduccion.png)

Entregables:

- Código en github, bien documentado.
- Informe que describe la arquitectura de seguridad de su prototipo. (en el README)
- Video de experimento en AWS

### Creando Proyecto.

**Cree un proyecto java usando maven**

Para el siguiente laboratorio, se van a crear dos proyecto:

1. El primer proyecto tendra el LoginService.
2. El segundo proyecto tendra el Service.

```Crearproyecto
mvn archetype:generate -DgroupId=edu.escuelaing.arep.Servicios -DartifactId = LoginService -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

```Crearproyecto2
mvn archetype:generate -DgroupId=edu.escuelaing.arep.servicios -DartifactId = sERVICIOS -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

**Agregar properties en el pom de ambos proyectos**

```properties
  <properties>
    <prohect.build.sourceEncoding>UTF-8</prohect.build.sourceEncoding>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
  </properties>
```

**Agregar dependencias en el pom de ambos proyectos**

```dependencies
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.sparkjava</groupId>
      <artifactId>spark-core</artifactId>
      <version>2.9.3</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-simple</artifactId>
      <version>1.7.35</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.35</version>
    </dependency>
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.8.9</version>
    </dependency>
    <dependency>
      <groupId>org.jetbrains</groupId>
      <artifactId>annotations</artifactId>
      <version>RELEASE</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>
```

**Agregar plugins en el pom de ambos proyectos**

```plugins
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.0</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>3.0.1</version>
        <executions>
          <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals><goal>copy-dependencies</goal></goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

**Compilar Proyecto.**

Para compilar el proyecto debemos dirigirnos a la ruta de la carpeta del proyecto y abrir un cmd.

```Compilar
    mvn clean install
```

**Verificar que se crearon las dependencias en la carpeta target.**

![dependencias.png](https://i.postimg.cc/bYZJZSrP/dependencias.png)

### Pasos AWS.

1. Acceda a la maquina virtual
2. Instalar java.
3. Subir proyecto
4. Genere un par de llaves públicas y privadas
5. Exporte el certificado
6. Importe el certificado a un TrustStore
7. Correr Instancia de cada proyecto.
8. Cree la nueva regla de seguridad.
9. Realizar Prueba
10. Ubicación del Video.

#### 1. Acceda a la maquina virtual

![cuartaparte1.png](https://i.postimg.cc/sXqdp4z7/cuartaparte1.png)

![cuartaparte2.png](https://i.postimg.cc/ZnNkjvYg/cuartaparte2.png)

![cuartaparte3.png](https://i.postimg.cc/sgV8GNWb/cuartaparte3.png)

![cuartaparte4.png](https://i.postimg.cc/htpYL1bv/cuartaparte4.png)

![llaveaws-Login.png](https://i.postimg.cc/CMnhhyrL/llaveaws-Login.png)

![llave-Servicioaws.png](https://i.postimg.cc/9FGc9dj6/llave-Servicioaws.png)

![maquinas.png](https://i.postimg.cc/wj2Hf9MF/maquinas.png)

![maquina-Servicio.png](https://i.postimg.cc/ZRBS53cD/maquina-Servicio.png)

#### 2. Instalar java.

```instalanadoJava
   sudo yum install java-1.8.0
```

```instalanadoJava2
   sudo yum install java-1.8.0-openjdk-devel
```

#### 3. Subir proyecto

Se crea un archivo .zip de los dos proyectos. Donde cada uno se sube a una máquina 
distinta con el fin de poder llevar a cabo las pruebas.

![subir-Servicios-Aws.png](https://i.postimg.cc/QdxvM1Nk/subir-Servicios-Aws.png)

![unzip-Servicios-aws.png](https://i.postimg.cc/jjD9HT7D/unzip-Servicios-aws.png)

#### 4. Genere un par de llaves públicas y privadas

**Login.**

```llavesLogin
   keytool -genkeypair -alias ecikeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ecikeystore.p12 -validity 3650
```

**Servicio.**

```llavesServicio
   keytool -genkeypair -alias ecikeypairservicio -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ecikeystoreservicio.p12 -validity 3650
```

#### 5. Exporte el certificado

**Login.**

```certificadoLogin
   keytool -export -keystore ./ecikeystore.p12 -alias ecikeypair -file ecicertLogin.cer
```

**Servicio.**

```certificadoServicio
   keytool -export -keystore ./ecikeystoreservicio.p12 -alias ecikeypair -file ecicertservicio.cer
```

#### 6. Importe el certificado a un TrustStore

**Login.**

```TrustStoreLogin
   keytool -import -file ./ecicert.cer -alias firstCA -keystore myTrustStoreservicio
```

```TrustStoreLoginDos
   keytool -import -file ./ecicertservicio.cer -alias firstCAServicio -keystore myTrustStoreservicio
```

**Servicio.**

```TrustStoreServicio
   keytool -import -file ./ecicertservicio.cer -alias firstCAServicio -keystore myTrustStore
```

```TrustStoreServicioDos
   keytool -import -file ./ecicert.cer -alias firstCA -keystore myTrustStore
```

#### 7. Correr Instancia de cada proyecto.

**Servicio.**

```InstanciaServicio
   java -cp "target/classes:target/dependency/*" edu.escuelaing.arep.servicios.Servicios
```

**Login.**

```instanciaLogin
   java -cp "target/classes:target/dependency/*" edu.escuelaing.arep.segurdiadLogin.Servicios.SparkWebAppSecure
```

#### 8. Cree la nueva regla de seguridad.

![Puerto35001.png](https://i.postimg.cc/8cXMB2WC/Puerto35001.png)

![Puerto35000.png](https://i.postimg.cc/NjHmmP0g/Puerto35000.png)

#### 9. Realizar Prueba

**Servicio.**

![pruebaservicioaws.png](https://i.postimg.cc/J0NSsQBK/pruebaservicioaws.png)

**Login.**

![prueba-Loginaws.png](https://i.postimg.cc/T1nvr6BV/prueba-Loginaws.png)

![prueba-Loginaws-Dos.png](https://i.postimg.cc/t4BQdDvM/prueba-Loginaws-Dos.png)

![prueba-Loginaws-Tres.png](https://i.postimg.cc/pdSw4rHQ/prueba-Loginaws-Tres.png)

![prueba-Loginaws-Cuatro.png](https://i.postimg.cc/G2sWCRLW/prueba-Loginaws-Cuatro.png)

#### 10. Ubicación del Video.

El video se encuentra en el proyecto con el nombre: VideoObteniendoServicio.mp4

hay podra descargarlo y verlo.

[VideoObteniendoServicio][id/namevideo]

[id/namevideo]: https://github.com/DeividMedina30/LabArep_AplicacionSegura/blob/master/VideoObteniendoServicio.mp4

### PASOS PARA CLONAR.

-  Nos dirigimos a la parte superior de nuestra ubicación, donde daremos clic y escribimos la palabra cmd, luego damos enter, con el fin de desplegar
   el Command Prompt, el cual es necesario.

![img1.png](https://i.postimg.cc/GmSNVZZL/img1.png)

![Imagen2.png](https://i.postimg.cc/vB5N1DDT/Imagen2.png)

![Imagen3.png](https://i.postimg.cc/T3hNVthZ/Imagen3.png)

- Una vez desplegado el Command Prompt, pasamos a verificar que tengamos instalado git, ya que sin él no podremos realizar la descarga.
  Para esto ejecutamos el siguiente comando.

`git --version`

![Imagen4.png](https://i.postimg.cc/nh5R0qDM/Imagen4.png)

- Si contamos con git instalado, tendra que salir algo similar. La version puede variar de cuando se este realizando este tutorial.
  Si no cuenta con git, puede ver este tutorial.

[Instalación de Git][id/name]

[id/name]: https://www.youtube.com/watch?v=cYLapo1FFmA

![Imagen5.png](https://i.postimg.cc/fR6CxZG9/Imagen5.png)

-  Una vez comprobado de que contamos con git. pasamos a escribir el siguiente comando. git clone,
   que significa que clonamos el repositorio, y damos la url del repositorio.

`$ git clone https://github.com/DeividMedina30/LabArep_AplicacionSegura.git`

![Imagen6.png](https://i.postimg.cc/gjkHY0Zf/Imagen6.png)

- Luego podemos acceder al proyecto escribiendo.

`$ cd LabArep_AplicacionSegura`

### TECNOLOGÍAS USADAS PARA EL DESARROLLO DEL LABORATORIO.

* [Maven](https://maven.apache.org/) - Administrador de dependencias.

* [GitHub](https://github.com/) - Forja para alojar proyectos utilizando el sistema de control de versiones Git.

* [Spark](http://sparkjava.com/) - Spark Framework es un DSL de marco web Java/Kotlin.

* [AWS](https://aws.amazon.com/es/free/?trk=eb709b95-5dcd-4cf8-8929-6f13b8f2781f&sc_channel=ps&sc_campaign=acquisition&sc_medium=ACQ-P|PS-GO|Brand|Desktop|SU|Core-Main|Core|LATAMO|ES|Text&ef_id=EAIaIQobChMIoueptLLJ9gIVw52GCh2YxwNgEAAYASAAEgIqMPD_BwE:G:s&s_kwcid=AL!4422!3!561348326837!e!!g!!aws&ef_id=EAIaIQobChMIoueptLLJ9gIVw52GCh2YxwNgEAAYASAAEgIqMPD_BwE:G:s&s_kwcid=AL!4422!3!561348326837!e!!g!!aws&all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all) - MongoDB es un sistema de base de datos NoSQL

### LIMITACIONES.

Por parte de las limitaciones tenemos qeu solamente estamos ofreciendo un servicio, además de 
validar usuario que ya están previamente creados en una clase. Además de no poder dejar 
corriendo las maquinas ya que se terminarian los creditos.

### EXTENDER.

Se espera que se logre extender permitiendo que se creen n usuarios donde se almacenaran
en una base de datos, además de poder crear distintos servicios con el fin de sacar el mayor
provecho.

### Documentación

Para generar la documentación se debe ejecutar:

`$ mvn javadoc:javadoc`

Esta quedará en la carpeta target/site/apidocs

### AUTOR.

> Deivid Sebastián Medina Rativa.

### Licencia.

Este laboratorio esta bajo la licencia de GNU GENERAL PUBLIC LICENSE.