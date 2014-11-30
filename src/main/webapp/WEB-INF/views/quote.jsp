<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<html>
<body>
	<form:form id="form" method="post" modelAttribute="policyForm">

		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable">
					<!-- Only required for left/right tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a id="n_0" href="#tab1" data-toggle="tab">
								Driver </a></li>
						<li><a id="n_1" href="#tab2" data-toggle="tab"> Vehicle </a></li>
						<li><a id="n_2" href="#tab3" data-toggle="tab"> Driving
								History </a></li>
						<li><a id="n_4" href="#tab5" data-toggle="tab">Quote</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<div class="progress progress-striped" style="width: 70%">
								<div class="bar" style="width: 20%;"></div>
								<span class="span1"> 20% </span>
							</div>
							<legend>Driver Details</legend>
							<div>
								<span class="help-block"> Enter your first name </span> 
								<form:errors path="driver.firstName" />
								<form:input class="span3" path="driver.firstName" placeholder="First Name" />
							</div>
							<div>
								<span class="help-block"> Enter your last name</span> 
								<form:errors path="driver.lastName" />
                                <form:input class="span3" path="driver.lastName" placeholder="Last Name" />
							</div>
							<div>
								<span class="help-block"> Enter your email </span> 
								<form:errors path="driver.email" />
                                <form:input class="span3" path="driver.email" placeholder="name@email.com" />
							</div>
							<span class="help-block"> Enter your address </span> 
							<form:input class="span2" path="driver.addrLine1" />
							<form:input class="span2" path="driver.addrLine2" />
                            <form:input class="span2" path="driver.city" />
							<form:input class="span2" path="driver.state" />
                            <form:input class="span1" path="driver.zip" />

							<div style="padding-top: 20px;"></div>
							<div>
                                <span class="help-block"> Gender </span> 
	                            <form:select path="driver.gender" class="span2">
	                                <option>Male</option>
	                                <option selected="selected">Female</option>
                                </form:select> 
                            </div>
							<div>
                                <span class="help-block"> Is Married </span> 
                                <form:checkbox path="driver.isMarried"/>
                            </div>
							
							<hr>
							<span class="help-block"> What is the desired effective date of this policy? </span>
							<div class="input-append date" id="dp1" data-date-format="mm-dd-yyyy">
								<form:input class="span2" size="16" path="effectiveDate"/> 								
								<span class="add-on"><i class="icon-th"></i></span>
							</div>
							<span class="help-block"> Date of Birth </span>
							<div class="input-append date" id="dp2" data-date-format="mm-dd-yyyy">
								<form:input class="span2" path="driver.birthDate" size="16" />
								<span class="add-on"><i class="icon-th"></i></span>
							</div>

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
							<legend>Vehicle Details</legend>

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
							</select> <span class="help-block"> Odometer Reading </span> <input
								name="vin" type="text" class="span3" /> <br /> <span
								class="help-block"> Please Enter your License State </span> <select>
								<option>MA</option>
								<option>NJ</option>
								<option>CT</option>
							</select> <span class="help-block"> Please Enter driver's License
								Number </span> <input type="text" class="span2"
								placeholder="License No."> <span class="help-block">
								Please tell us the owner type </span> <select>
								<option>Leased</option>
								<option>Owned</option>
								<option>Business</option>
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

							<span class="help-block"> Annual Mileage </span> <input name="fn"
								type="text" class="span3" value="Joan" placeholder="Mileage" />

							<div>
								<span class="help-block"> Accident Type? <select
									class="span6" onChange="shw_mr_f_appcbl(this)">
										<option>COLLISSION</option>
										<option>MINOR</option>
										<option>TOTALED</option>


								</select>
								</span>
							</div>

							</span>
							<div>
								<button type="button" class="btn btn-primary"
									onclick="$('#n_1').click();">Back</button>
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

							<div style="padding-bottom: 20px">
								Click below to get your quote.
								<div>
									<div class="modal hide fade in" id="fnlqtmdl">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">�</button>
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
									<button id="fnlqt" type="submit" class="btn">Generate
										Quote!</button>
								</div>
							</div>
							<div>
								<button type="button" class="btn btn-primary"
									onclick="$('#n_2').click();">Back</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>
