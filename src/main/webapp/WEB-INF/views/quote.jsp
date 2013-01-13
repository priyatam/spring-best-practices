<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable">
				<!-- Only required for left/right tabs -->
				<ul class="nav nav-tabs">
					<li class="active"><a id="n_0" href="#tab1" data-toggle="tab">
							Your Info </a></li>
					<li><a id="n_1" href="#tab2" data-toggle="tab"> Vehicle
							Info </a></li>
					<li><a id="n_2" href="#tab3" data-toggle="tab"> Insurance
							History </a></li>
					<li><a id="n_3" href="#tab4" data-toggle="tab">Discount</a></li>
					<li><a id="n_4" href="#tab5" data-toggle="tab">Quote</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">
						<div class="progress progress-striped" style="width: 70%">
							<div class="bar" style="width: 20%;"></div>
							<span class="span1"> 20% </span>
						</div>
						<legend>Enter Details</legend>
						<div>
							<span class="help-block"> Enter your first name and an
								optional Middle Initial, if any. </span> <input name="fn" type="text"
								class="span3" value="Joan" placeholder="First Name" /> <input
								name="mi" type="text" class="span1" placeholder="MI" />
						</div>
						<div>
							<span class="help-block"> Enter your last name and suffix
								if applicable. </span> <input name="ln" type="text" class="span3"
								value="Miller" placeholder="Last Name"> <select
								name="sfx" class="span2">
								<option></option>
								<option>Jr.</option>
								<option>Sr.</option>
								<option>II</option>
								<option>III</option>
								<option>IV</option>
							</select>
						</div>
						<span class="help-block"> Enter your address </span> <input
							name="strtno" type="text" class="span2" placeholder="Street No.">
						<input name="strtnm" type="text" class="span3"
							placeholder="Street Name"> <input name="strtunt"
							type="text" class="span3" placeholder="Apt./Unit/Suite">
						<hr>
						<span class="help-block"> What is the desired effective
							date of this policy? </span>
						<div class="input-append date" id="dp1"
							data-date-format="mm-dd-yyyy">
							<input class="span2" size="16" type="text" /> <span
								class="add-on"><i class="icon-th"></i></span>
						</div>
						<span class="help-block"> Date of Birth </span>
						<div class="input-append date" id="dp2"
							data-date-format="mm-dd-yyyy">
							<input class="span2" value="08-15-1978" size="16" type="text" />
							<span class="add-on"><i class="icon-th"></i></span>
						</div>
						<div style="padding-top: 20px;"></div>
						<select name="sx" class="span2">
							<option>Male</option>
							<option selected="selected">Female</option>
						</select> <select name="mrtlstts" class="span2">
							<option>Single</option>
							<option>Married</option>
							<option>Divorced</option>
						</select>
						<div style="padding-top: 20px;"></div>
						<span class="help-block"> Are all your, vehicles registered
							in MA ? </span> <input type="checkbox" value="Yes"> Yes <input
							type="checkbox" value="No"> No
						<div style="padding-top: 20px;"></div>
						<div>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_1').click();">Next</button>
						</div>
					</div>
					<div class="tab-pane" id="tab2">
						<div class="progress progress-striped" style="width: 70%">
							<div class="bar" style="width: 40%;"></div>
							<span class="span1"> 40% </span>
						</div>
						<span class="help-block"> Enter the 17 digit VIN number or
							enter a year, make, model </span> <input name="vin" type="text"
							class="span3" placeholder="VIN" /> <br /> <select>
							<option>2000's</option>
							<option>1990's</option>
							<option>1980's</option>
						</select> <select>
							<option>Ford</option>
							<option>Audi</option>
							<option>BMW</option>
						</select> <select>
							<option>Aspire</option>
							<option>Candy</option>
							<option>Icon</option>
						</select> <span class="help-block"> Please Enter your License State
						</span> <select>
							<option>MA</option>
							<option>CA</option>
						</select> <span class="help-block"> Please Enter driver's License
							Number </span> <input type="text" class="span2"
							placeholder="License No."> <span class="help-block">
							Is the vehicle used for business ? </span> <input type="checkbox"
							value="Yes"> Yes <input type="checkbox" value="No">
						No <span class="help-block"> Is this vehicle garaged at a
							location other than your residence ? </span> <input type="checkbox"
							value="Yes"> Yes <input type="checkbox" value="No">
						No <span class="help-block"> Please tell us if this car is
							leased or financed </span> <select>
							<option>Leased</option>
							<option>Financed</option>
							<option>Not Leased or Financed</option>
						</select>
						<div>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_0').click();">Back</button>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_2').click();">Next</button>
						</div>
					</div>
					<div class="tab-pane" id="tab3">
						<div class="progress progress-striped" style="width: 70%">
							<div class="bar" style="width: 60%;"></div>
							<span class="span1"> 60% </span>
						</div>
						<div>
							<span class="help-block"> In the last 12 months, have you
								had auto insurance continuously, with no lapse in coverage ? <select
								class="span6" onChange="shw_mr_f_appcbl(this)">
									<option>Yes, I've had my own insurance policy</option>
									<option>Yes, I've been insured on my parents policy</option>
									<option>Yes, I've been insured on someone else's
										policy</option>
									<option>Yes, I've been insured on someone else's
										policy</option>
									<option>No, I've had insurance for less than a year</option>
									<option>No, I've had a car and no insurance</option>
							</select>
							</span>
						</div>
						<span class="help-block"> If you have a home, condo,
							renter's insurance policy, please select the insurance company. </span>
						<select class="span3">
							<option>AIG</option>
							<option>Amica</option>
							<option>Hanover</option>
							<option>Safety</option>
							<option>Other</option>
						</select> <span class="help-block"> How did you hear about Us ? <select>
								<option>Friend / Relative</option>
								<option>Link From Agent's Site</option>
								<option>Search</option>
								<option>TV Add</option>
								<option>Print Add</option>
								<option>Other</option>
						</select>
						</span>
						<div>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_1').click();">Back</button>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_3').click();">Next</button>
						</div>
					</div>
					<div class="tab-pane" id="tab4">
						<div class="progress progress-striped" style="width: 70%">
							<div class="bar" style="width: 80%;"></div>
							<span class="span1"> 80% </span>
						</div>
						<div>
							<strong>To retain your discount, you must register for e
								services and select eDocument delivery within 30 days of
								purchasing your policy </strong>
						</div>
						<div>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_2').click();">Back</button>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_4').click();">Next</button>
						</div>
					</div>
					<div class="tab-pane" id="tab5">
						<div class="progress progress-striped"
							style="width: 70%; float: left;">
							<div class="bar" style="width: 100%;"></div>
						</div>
						<div style="margin-left: 71%">Done!</div>
						<div style="clear: both"></div>
						Please Enter your email, to get a permanant link of the Quote.
						<div>
							<input type="text" class="span3">
						</div>
						<div style="padding-bottom: 20px">
							Click below to get your quote.
							<div>
								<div class="modal hide fade in" id="fnlqtmdl">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">×</button>
										<h3 id="myModalLabel">Quote for Joan</h3>
									</div>
									<div class="modal-body">
										<table>
											<caption></caption>
											<thead>
												<tr>
													<th></th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>...</td>
													<td>...</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<button id="fnlqt" type="button" class="btn">Quote</button>
							</div>
						</div>
						<div>
							<button type="button" class="btn btn-primary"
								onclick="$('#n_3').click();">Back</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
