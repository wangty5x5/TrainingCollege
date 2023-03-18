package action;

import org.springframework.stereotype.Controller;

/**
 *
 */
@Controller
public class MainAction extends BaseAction {

    @Override
    public String execute(){
        return "main";
    }
}
