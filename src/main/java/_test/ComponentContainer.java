package _test;

import java.util.List;
import java.util.Optional;

public interface ComponentContainer {
    List<Component> containedComponents();

    default <T extends Component> Optional<T> getComponent(Class<T> componentClazz) {
        return containedComponents().stream()
                .filter(componentClazz::isInstance)
                .map(componentClazz::cast)
                .findFirst();
    }
}