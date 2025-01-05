package _test;

import java.util.ArrayList;
import java.util.List;

public class Entity implements ComponentContainer {
    private final List<Component> components = new ArrayList<>();

    @Override
    public List<Component> containedComponents() {
        return components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Class<? extends Component> componentClazz) {
        components.removeIf(componentClazz::isInstance);
    }
}