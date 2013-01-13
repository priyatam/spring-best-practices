<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav nav-list">
	<li class="nav-header">Quick Links</li>
	<li><a href="#" onclick="$('#modalFAQ').modal()">FAQ</a></li>
	<li><a href="#" onclick="$('#modalTerms').modal()">Terms and conditions</a></li>
	<li class="nav-header">Customer Service</li>	

	<div class="modal hide fade in" id="modalFAQ">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">�</button>
			<h3 id="modalFAQLabel">FAQ</h3>
		</div>
		<div class="modal-body">
			<ol>
				<li>Is this site secure? <br /> Yes it is. We use
					128-bit encryption and SSL and certificate services of VeriSign,
                    a standard used by many websites when submitting confidential data 
                    such as credit card information over the internet.
				</li>
				<li>How long is this going to take?<br /> Most people can get a
					quote in 10 minutes. If you need to leave your computer during
					your quote, the information you entered is automatically saved for
					up to 60 days.
				</li>
				<li>Why do you need so much information about me?<br /> The
					information about your driving experience, your vehicles, and the
					driving experience of other drivers in your household is necessary
					to provide you an accurate quote. We also request information to
					determine whether there are factors that may make you eligible for
					a more favorable rate. Factors such as good driving experience,
					vehicle safety features and membership in a motor club could have a
					positive effect on your rate.
				</li>
				<li>Will I definitely get a quote?<br /> Most people are able
					to get a quote online. However, in some cases it may be necessary
					for you to speak with a representative for assistance. We will let
					you know if your circumstances require you to speak with a
					representative.
				</li>
				<li>Am I obligated to buy the policy if I get a quote?<br />
					No, you do not have to purchase a policy if you
					get a quote.
				</li>
				<li>Will you sell my information to any third parties?<br /> We
					will not sell your information to a third party. Please
					review our Privacy Policy for more information on how we use and
					protect your personal information.
				</li>
				<li>What effective date can I select for my policy?</br> You can
					select any date between tomorrow and the next 60 days for your
					coverage to start.
				</li>
				<li>Q: What is the difference between a "policy owner" and a
					"driver"?<br /> The policy owner is the person or people that own,
					lease or finance the vehicles to be insured, and, if a policy is
					purchased, the person or people that have an insurance agreement
					with Plymouth Rock. A driver is any person that lives in your
					household that has a driver's license, or any person that
					customarily drives a car on your policy. Drivers are also covered
					under your insurance policy. You will have the opportunity to add
					drivers to your policy later. Policy owners will automatically be
					listed drivers on your policy.
				</li>
				<li>How do I retrieve my quote later?<br /> If we have your
					email address, we'll send you a quote confirmation email that will
					include your Quote Number and a link to retrieve your quote. You
					may also contact us at
					1-800-FRE-INSU. We store your quote information for up to 60
					days.
				</li>
				</pre>
		</div>
	</div>

	<div class="modal hide fade in" id="modalTerms">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">�</button>
			<h3 id="modalTermsLabel">Terms &amp; Conditions</h3>
		</div>
		<div class="modal-body">
			<p>
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris
				consequat, ipsum non adipiscing vestibulum, diam arcu scelerisque
				nisl, sed commodo lectus risus eu magna. Etiam leo eros, pretium id
				adipiscing et, eleifend eget metus. Duis in justo risus. Nullam in
				libero nulla. Morbi ut nunc nulla. Etiam tincidunt, risus sed
				vehicula hendrerit, felis mi dapibus tortor, quis sollicitudin
				libero risus eu lectus.
			</p>
		</div>
	</div>
</ul>