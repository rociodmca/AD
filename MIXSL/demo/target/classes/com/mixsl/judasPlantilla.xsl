<?xml version="1.0" encoding='utf-8'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match='/'>
   <html><xsl:apply-templates /></html>
 </xsl:template>
 <xsl:template match='catalogo'>
    <head><title>LISTADO DE CDS</title></head>
    <body> 
    <h1>LISTA DE CDS</h1>
    <table border='1'>
    <tr><th>TITULO</th><th>ARTISTA</th><th>PAIS</th><th>COMPAÑIA</th><th>PRECIO</th><th>AÑO</th></tr>
      <xsl:apply-templates select='cd' />
    </table>
    </body>
 </xsl:template>
 <xsl:template match='cd'>
   <tr><xsl:apply-templates /></tr>
 </xsl:template>
 <xsl:template match='titulo|artista|pais|compania|precio|ano'>
   <td><xsl:apply-templates /></td>
 </xsl:template>
</xsl:stylesheet>