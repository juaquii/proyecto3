package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Usuario {
    private String nombre;
    private String email;
    private String password;

    public Usuario(int id, String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = hashPassword(password);
    }

    public Usuario(String usuario, String contrasenaHash, String nombre, String correoElectronico, boolean s) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("El email debe ser válido y tener el dominio @gmail.com");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public static boolean autenticarUsuario(String email, String password) {
        Usuario usuario = buscarUsuario(email);
        if (usuario != null) {
            String hashedPassword = hashPassword(password);
            return usuario.getPassword().equals(hashedPassword);
        }
        return false;
    }

    private static Usuario buscarUsuario(String email) {
        // Implementar la lógica para buscar un usuario en la base de datos
        // Por ahora, solo devuelve null
        return null;
    }

    private static boolean isValidEmail(String email) {
        String pattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return Pattern.matches(pattern, email);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}