package eu.andreatt.ejerciciol_dein.util;

import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
    private static final Properties props = new Properties();

    static {
        // Carga el archivo de propiedades desde el classpath
        try (InputStream input = Propiedades.class.getResourceAsStream("/eu/andreatt/ejercicioi_dein/configuration.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo configuration.properties en el classpath.");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el valor asociado a una clave desde el archivo de propiedades situado en el classpath.
     *
     * @param clave La clave cuyo valor se desea obtener.
     * @return El valor asociado a la clave.
     * @throws RuntimeException Si la clave no tiene un valor asociado en el archivo de propiedades.
     */
    public static String getValor(String clave) {
        String valor = props.getProperty(clave);
        if (valor != null) {
            return valor;
        }else {
            throw new RuntimeException("Clave '" + clave + "' no encontrada en el archivo de propiedades.");
        }
    }
}