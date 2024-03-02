public class ResumenExamenParte2AD {
    //persistence
    ///////mysql
    /* <?xml version="1.0" encoding="UTF-8"?>
        <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
                https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd" version="3.0">
                
            <persistence-unit name="Controlador" > <!-- Clase que utilizo para realizar la persistencia-->

                <class>com.ejemplohibernate.Usuario</class>
                <class>com.ejemplohibernate.DireccionPrincipal</class>
                <class>com.ejemplohibernate.Libro</class>
                <class>com.ejemplohibernate.Materia</class>



                <properties>
                <!-- Datos de conexión-->
                    <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:33060/judas2" /> 
                    <property name="javax.persistence.jdbc.user" value="root" />
                    <property name="javax.persistence.jdbc.password" value="secret" />
                    <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
                <!-- Fin Datos de conexión-->
                    <property name="hibernate.show_sql" value="true" /> <!-- Mostramos las consultas SQL generadas-->
                    <property name="hibernate.format_sql" value="true" /> <!-- -->
                    <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" /><!--Dialecto con la q se habla bbdd -->
                    <property name="hibernate.hbm2ddl.auto" value="create"/> <!-- "validate", "update", "create", y "create-drop" -->
                </properties>
                

            </persistence-unit>
        </persistence> */
    ////////oracle
    /*
     * <?xml version="1.0" encoding="UTF-8"?>
        <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
                https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd" version="3.0">
                
            <persistence-unit name="Controlador" > <!-- Clase que utilizo para realizar la persistencia-->

                <class>com.hibernateoracle.Usuario</class>

                <properties>
                <!-- Datos de conexión-->
                    <property name="javax.persistence.jdbc.url" value="jdbc:oracle:thin:@localhost:1521/FREE" /> 
                    <property name="javax.persistence.jdbc.user" value="usuario" />
                    <property name="javax.persistence.jdbc.password" value="secret" />
                    <property name="javax.persistence.jdbc.driver" value="oracle.jdbc.OracleDriver" />
                <!-- Fin Datos de conexión-->
                    <property name="hibernate.show_sql" value="true" /> <!-- Mostramos las consultas SQL generadas-->
                    <property name="hibernate.format_sql" value="true" /> <!-- -->
                    <property name="hibernate.dialect" value="org.hibernate.dialect.OracleDialect" /><!--Dialecto con la q se habla bbdd -->
                    <property name="hibernate.hbm2ddl.auto" value="create"/> <!-- "validate", "update", "create", y "create-drop" -->
                </properties>
                

            </persistence-unit>
        </persistence>
     */
    //////////postgresql
    /*
     * <?xml version="1.0" encoding="UTF-8"?>
        <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
                https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd" version="3.0">
                
            <persistence-unit name="Controlador" > <!-- Clase que utilizo para realizar la persistencia-->

                <class>com.hibernatepostgresql.Usuario</class>
                <class>com.hibernatepostgresql.DireccionPrincipal</class>
                <class>com.hibernatepostgresql.Libro</class>

                <properties>
                <!-- Datos de conexión-->
                    <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/judas" /> 
                    <property name="javax.persistence.jdbc.user" value="postgres" />
                    <property name="javax.persistence.jdbc.password" value="secret" />
                    <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
                <!-- Fin Datos de conexión-->
                    <property name="hibernate.show_sql" value="true" /> <!-- Mostramos las consultas SQL generadas-->
                    <property name="hibernate.format_sql" value="true" /> <!-- -->
                    <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect" /><!--Dialecto con la q se habla bbdd -->
                    <property name="hibernate.hbm2ddl.auto" value="create"/> <!-- "validate", "update", "create", y "create-drop" -->
                </properties>
                

            </persistence-unit>
        </persistence>
     */

    //pom
    /*
     * <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.1.3.Final</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
        <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11 -->
        <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc11</artifactId>
        <version>23.2.0.0</version>
        </dependency>
        <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.30</version>
        </dependency>
     */


    //Anotaciones de hibernate
    /* 
    @Entity
    @Table(name = "libro") //ojete en minúscula o mayúscula. Case-sensitive
    //class
    Ej1:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "isbn")
    private String isbn;

    Ej2:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "edad")
    private int edad;
    //@Column(name = "direccion")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private DireccionPrincipal direccion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<Libro>();

    //Aquí hay una relación de muchos a muchos!!!
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actores_peliculas", catalog = "db_peliculas", joinColumns = 
    {@JoinColumn(name = "id_actor", nullable = false, updatable = false)}, 
    inverseJoinColumns = {@JoinColumn(name = "id_pelicula", nullable = false, updatable = false)})

    //Temporal
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", length = 10)

    //Ojo! Relación 1:N 1 director tiene muchas peliculas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director")  //obligatorio mapearlo por ser 1 a n
    //Aquí va a ir una relación de muchos a uno (pelicula)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_director")
    */

    ///https://mkyong.com/tutorials/hibernate-tutorials/
}
