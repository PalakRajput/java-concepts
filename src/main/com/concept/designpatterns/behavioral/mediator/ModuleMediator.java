package src.main.com.concept.designpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModuleMediator {
    List<Module> modules = new ArrayList<>();

    public boolean isModuleAvailable(String moduleName){
        Optional<Module> moduleOptional = modules.stream().filter(m -> m.getName().equals(moduleName)).findFirst();
        return moduleOptional.isPresent();
    }

    public void addModule(Module module){
        modules.add(module);
    }

    public void performAction(String moduleName){
        if(isModuleAvailable(moduleName)){
            System.out.println("Performed action for " + moduleName + " module.");
        } else {
            System.out.println("Module " + moduleName + " is not present.");
        }
    }
}
