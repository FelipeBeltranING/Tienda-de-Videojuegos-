package usecases.dto;

public class OperationResult {
    private final boolean success;
    private final String message;

    // El constructor es privado para obligar a usar los métodos estáticos .ok() y .fail()
    private OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static OperationResult ok(String message) {
        return new OperationResult(true, message);
    }

    public static OperationResult fail(String message) {
        return new OperationResult(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
