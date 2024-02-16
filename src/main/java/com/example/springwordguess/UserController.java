package com.example.springwordguess;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String Homepage(){
        return "Homepage";
    }
    @GetMapping("/Login")
    public String loginPage(){
        return "Login";
    }


    @PostMapping("/loginValidator")
    public String loginValidator(@RequestParam String email, @RequestParam String
            password, HttpSession session, Model model) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "redirect:/game";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }
    @PostMapping("/signupPage")
    public String signupPage() {
        return "Signup";
    }

    @PostMapping("/signupValidation")
    public String signupValidation(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) {
        // Check if the email is already registered
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            model.addAttribute("error", "Email is already registered");
            return "Signup"; // Return the name of your signup HTML template
        }

        // Server-side validation
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            model.addAttribute("error", "Please fill in all fields.");
            return "Signup"; // Return the name of your signup HTML template
        }

        if (name.length() < 4 || email.length() < 4 || password.length() < 4) {
            model.addAttribute("error", "All fields must contain at least 4 characters.");
            return "Signup"; // Return the name of your signup HTML template
        }

        if (!password.matches(".*[A-Z].*")) {
            model.addAttribute("error", "Password must contain at least one capital letter.");
            return "Signup"; // Return the name of your signup HTML template
        }

        if (!password.matches(".*\\d.*")) {
            model.addAttribute("error", "Password must contain at least one digit.");
            return "Signup"; // Return the name of your signup HTML template
        }

        // Create a new user object and save it to the database
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return "redirect:/Login"; // Redirect the user to the login page
    }


    @GetMapping("/Dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "Dashboard";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}