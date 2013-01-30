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
				<li>Is this site secure? <br /> Yes. We use 128-bit encryption and SSL.
				</li>
				<li>How long will this take?<br /> 5 to 10 minutes.
				</li>
				<li>Why do you need so much information about me?<br /> So we can provide
                    you with the best rate.
				</li>
				<li>Will I definitely get a quote?<br /> We will try our best.
				</li>
				<li>Am I obligated to buy the policy if I get a quote?<br />
					No.
				</li>
				<li>Will you sell my information to any third parties?<br />
                    No.
				</li>
				<li>How do I retrieve my quote later?<br /> We'll send you a quote confirmation 
                    email that will include your Quote Number and a link to retrieve your quote.
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
			</p>
		</div>
	</div>
</ul>