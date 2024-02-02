<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match='/'>
   <html><xsl:apply-templates /></html>
 </xsl:template>
 <xsl:template match='ciudades'>
    <head><title>Ejercicio1</title></head>
    <body> 
    <font color="#FF0000"><h1>&#161;&#161;Esto es el examen!   29/11/2022</h1></font>
    <table border='1px' width="800px" solid="#b3adad" border-collapse="collapse" padding="5px">
    <tr bgcolor="#a9d3d3"><th>Ciudad</th><th>Temperatura</th><th>Presion</th><th>Nombre</th><th>Apellidos</th></tr>
      <xsl:apply-templates select='ciudad' />
    </table>
    </body>
 </xsl:template>
 <xsl:template match='ciudad'>
  <tr bgcolor="#d8ebeb">
    <td align="center"><xsl:value-of select="nombre"/></td>
    <td align="center"><xsl:value-of select="temperatura"/></td>
    <td align="center"><xsl:value-of select="presion"/></td>
    <td align="center"><xsl:value-of select="nombreApe"/></td>
    <td align="center"><xsl:value-of select="nombreApe/@apellido"/></td>
  </tr>
 </xsl:template>
</xsl:stylesheet>