import java.util.Objects;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    public Resume(String uuid) {
        this.uuid = Objects.requireNonNull(uuid, "uuid is null");
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
