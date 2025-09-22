import java.util.Scanner;

// Implementación genérica de Pila en Java
 class Pila<T> {
    private Nodo<T> cima;

    // Clase interna Nodo
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Constructor
    public void Pila() {
        cima = null;
    }

    // Apilar
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    // Desapilar
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    // Ver cima
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.dato;
    }

    // Verificar si está vacía
    public boolean isEmpty() {
        return cima == null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pila<String> pilaPrincipal = new Pila<>();
        Pila<String> pilaSecundaria = new Pila<>();

        int opcion;

        do {
         //Presentamos lista de opciones
            System.out.println("\n=== MENU ===");
            System.out.println("1. Escribir texto");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Mostrar texto actual (Peek)");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Escribe el texto: ");
                    String texto = sc.nextLine();
                    pilaPrincipal.push(texto);
                    pilaSecundaria = new Pila<>(); // limpiar pila de rehacer
                    System.out.println("Texto guardado.");
                    break;

                case 2:
                    if (!pilaPrincipal.isEmpty()) {
                        String eliminado = pilaPrincipal.pop();
                        pilaSecundaria.push(eliminado);
                        System.out.println("Se deshizo: " + eliminado);
                    } else {
                        System.out.println("Nada que deshacer.");
                    }
                    break;

                case 3:
                    if (!pilaSecundaria.isEmpty()) {
                        String recuperado = pilaSecundaria.pop();
                        pilaPrincipal.push(recuperado);
                        System.out.println("Se rehizo: " + recuperado);
                    } else {
                        System.out.println("Nada que rehacer.");
                    }
                    break;

                case 4:
                    if (!pilaPrincipal.isEmpty()) {
                        System.out.println("Texto actual: " + pilaPrincipal.peek());
                    } else {
                        System.out.println("No hay texto escrito.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
