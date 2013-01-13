<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="row-fluid">
		<div class="span4">
			<h3>Best Coverage</h3>
			<p>Get a quote online, with service from local independent
				agents.</p>

			<div class="modal hide fade in" id="modal1">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">We have outlets all over MA</h3>
				</div>
				<div class="modal-body">
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris
						consequat, ipsum non adipiscing vestibulum, diam arcu scelerisque
						libero risus eu lectus.
					</p>
				</div>
			</div>
			<button id="viewDetails1" class="btn" onclick="$('#modal1').modal()"
				type="button">View Details</button>
		</div>

		<div class="span4">
			<h3>Exceptional service</h3>
			<p>
				From the moment you file a claim until your car is repaired, we're
				with you every step of the way &raquo;</a>
			</p>
		</div>

		<div class="span4">
			<h3>Instant Rewards</h3>
			<p>Enjoy extra discounts and savings from day one. You could save
				hundreds of dollars a year.</p>

			<div class="modal hide fade in" id="modal2">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">Get Your Rewards On Day One</h3>
				</div>
				<div class="modal-body">
					Our Rewards program saves time,  money, and delivers exceptional convenience: <br />
					<img src="img/rewards.jpg" /> <br />
					<ul>
						<li>Exceptional, personal service and individual attention</li>
						<li>Exclusive Savings Pass for 5-25% off auto-related
							products and services</li>
						<li>Easy account access, by phone or online, anytime,
							anywhere</li>
						<li>Best-in-class claim services that let you choose the
							repair option most convenient for you</li>
						<li>Guaranteed repairs from referral shops for
							as long as you own or lease your car</li>
						<li>eReminders* help remind you to renew your license,
							register your car and get your car inspected</li>
						<li>Get Home Safe provides one time, one way cab fare home,
							up to $50 per policy year, when you find yourself in a situation
							where it might be unsafe for you to drive (excluding mechanical
							breakdown).</li>
					</ul>
					... and best of all, you get to enjoy these rewards immediately —
					as soon as your policy becomes effective, you’re automatically
					enrolled. That’s more than just insurance!
				</div>
			</div>
			<button id="viewDetails2" class="btn" onclick="$('#modal2').modal()"
				type="button">View Details</button>
		</div>

	</div>

	<!-- 2nd row -->
	<div class="row-fluid">
		<div class="span4">
			<h3>Anywhere, anytime</h3>
			<p>Simplify your life with innovative mobile access and
				eServices.</p>

			<div class="modal hide fade in" id="modal3">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">Mobile</h3>
				</div>
				<div class="modal-body">
					Plymouth Rock eServices and mobile website are designed to meet the
					needs of your busy lifestyle. We will be there whenever and
					wherever you need us with complete online and mobile services
					through: <img src="img/coverage.png" />
					<ul>
						<li>Your computer</li>
						<li>Your smartphone</li>
						<li>Even through Tablets</li>
					</ul>
				</div>
			</div>
			<button id="viewDetails3" class="btn" onclick="$('#modal3').modal()"
				type="button">View Details</button>
		</div>

		<div class="span4 offset4">
			<h3>We're local, too</h3>
			<p>Our neighbors have relied on us since 1982 for Massachusetts
				and Connecticut auto insurance.</p>

			<div class="modal hide fade in" id="modal4">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">Who we are</h3>
				</div>
				<div class="modal-body">
					<p>
						Just your friendly Insurace company. 
					</p>
				</div>
			</div>
			<button id="viewDetails4" onclick="$('#modal4').modal()" class="btn"
				type="button">View Details</button>
		</div>
	</div>

</body>
</html>