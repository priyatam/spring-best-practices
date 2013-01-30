package github.priyatam.springrest.controller;

import github.priyatam.springrest.utils.AjaxUtils;
import github.priyatam.springrest.view.PolicyForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/quote")
@SessionAttributes("policyForm")
public class QuoteController {

	// Invoked on every request
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("policyForm")
	public PolicyForm getPolicyForm() {
		return new PolicyForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public void form() {
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@Valid PolicyForm policyForm, BindingResult result,
			@ModelAttribute("ajaxRequest") boolean ajaxRequest, Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return null;
		}

		// Typically you would save to a db and clear the "form" attribute from the session
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully.  Bound " + policyForm;

		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/policy";
		}
	}

}
