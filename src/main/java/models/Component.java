package models;

import java.util.HashMap;
import java.util.Map;

public class Component {

    private String name;
    private boolean isInstalled;
    private Map<String, Component> dependencies = new HashMap<>();
    private Map<String, Component> dependents = new HashMap<>();

    public Component(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

    public void addDependency(Component component){
        this.dependencies.put(component.getName(), component);
    }

    public void addDependent(Component component){
        this.dependents.put(component.getName(), component);
    }

    public Map<String, Component> getDependencies() {
        return dependencies;
    }

    public Map<String, Component> getDependents() {
        return dependents;
    }
}
