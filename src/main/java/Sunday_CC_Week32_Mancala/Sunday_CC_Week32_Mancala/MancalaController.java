package Sunday_CC_Week32_Mancala.Sunday_CC_Week32_Mancala;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("game")
public class MancalaController {

    private final MancalaService gameService;

    public MancalaController(MancalaService gameService) {
        this.gameService = gameService;
    }

    @ModelAttribute("game")
    public MancalaGame game() {
        return new MancalaGame();
    }

    @GetMapping("/")
    public String index(@ModelAttribute("game") MancalaGame game, Model model) {
        return "index";
    }

    @PostMapping("/move")
    public String move(@ModelAttribute("game") MancalaGame game, @RequestParam int pitIndex) {
        if (game.getCurrentPlayer() == 1 && (pitIndex < 0 || pitIndex > 5)) return "redirect:/";
        if (game.getCurrentPlayer() == 2 && (pitIndex < 7 || pitIndex > 12)) return "redirect:/";

        gameService.makeMove(game, pitIndex);
        return "redirect:/";
    }

    @PostMapping("/reset")
    public String reset(@ModelAttribute("game") MancalaGame game) {
        game.reset();
        return "redirect:/";
    }
}