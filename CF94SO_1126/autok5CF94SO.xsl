<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
            	<table>
					<xsl:for-each-group select="autok/auto" group-by="tipus">
                        <xsl:sort select="count(current-group())" order="descending"/>
                        <tr>
                            <td>
                                <xsl:value-of select="current-grouping-key()" />
                            </td>
                            <td>
                                <xsl:value-of select="count(current-group())" />
                            </td>
                        </tr>
                    </xsl:for-each-group>
                 </table>
            </body>
        </html>
    </xsl:template>    
</xsl:stylesheet>