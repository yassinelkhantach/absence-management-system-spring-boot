$(document).ready(function() {
	// Useful fnctions
	function getFormattedDateTime(date, hours, minutes) {
		var formattedDate = moment(date).format('YYYY-MM-DD');
		var formattedTime = moment().set('hour', hours).set('minute', minutes).format('HH:mm');
		return formattedDate + 'T' + formattedTime;
	}

	function checkUrlForPattern(url) {
		var regex = /\/teacher\/absences/;
		return regex.test(url);
	}
	
	
	

	if (checkUrlForPattern(window.location.href)) {
		var teacher_id = document.getElementById("teacher_id").value;
		//Load the manual guide modal first
		/*setTimeout(function() {
		  $('#manual-guide').modal('show');
		}, 1000);
		$('#manual-guide').on('hidden.bs.modal', function () {
		  $(this).remove(); // Remove the modal element from the DOM
		});*/
		
		var mySelect = document.getElementById("select-student-element");
		var selectedOptgroupInput = document.getElementById("selectedOptgroup");

		mySelect.addEventListener("change", function() {
			console.log("changed")
			var selectedOptgroup = mySelect.options[mySelect.selectedIndex].parentElement.getAttribute("value");;
			selectedOptgroupInput.value = selectedOptgroup;
		});
		/*useful data*/

		var today = new Date();
		var hours = today.getHours();
		var minutes = today.getMinutes();

		var updatedStart = null;
		var updatedEnd = null;

		var colorClasses = {
			1: 'bg-info',
			2: 'bg-success',
			3: '',
			4: 'bg-warning',
			5: 'bg-danger',
		};

		$('#teacher-calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: getFormattedDateTime(today, hours, minutes),
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: function(start, end, timezone, callback) {
				$.ajax({
					url: `http://localhost:8080/absences/teacher/${teacher_id}`,
					type: 'GET',
					success: function(response) {
						// Process the response and transform it into FullCalendar's event format
						var absences = [];
						response.forEach(function(absenceData) {
							var absence = {
								id: absenceData.id,
								title: `${absenceData.student_name}<b>${absenceData.element_label}`,
								start: absenceData.start, // assuming your event data has 'start' and 'end' properties
								end: absenceData.end,
								className: colorClasses[absenceData.session],
								justified: absenceData.justified,
								//url: `http://localhost:8080/teacher/students/${absenceData.student}/details`,
							}
							absences.push(absence);
						});
						callback(absences); // Pass the events to FullCalendar for rendering
					},
					error: function() {
						alert('There was an error while fetching absences!');
					}
				});
			},
			eventDrop: function(event, delta, revertFunc) {
				updatedStart = event.start.format();
				updatedEnd = event.end ? event.end.format() : null;
				var eventData = {
					id: event.id,
					start: updatedStart,
					end: updatedEnd
				};
				// Make the PUT request to update the event on the server
				$.ajax({
					url: `http://localhost:8080/absences/${event.id}`,
					type: 'PUT',
					contentType: "application/json", // Set the content type to JSON
					data: JSON.stringify(eventData),
					success: function(response) {
						console.log('Event updated successfully');
					},
					error: function() {
						revertFunc();
					}
				});

			},
			eventResize: function(event, delta, revertFunc) {
				updatedStart = event.start.format();
				updatedEnd = event.end ? event.end.format() : null;

				var eventData = {
					id: event.id,
					start: updatedStart,
					end: updatedEnd
				};
				// Make the PUT request to update the event on the server
				$.ajax({
					url: `http://localhost:8080/absences/${event.id}`,
					type: 'PUT',
					contentType: "application/json", // Set the content type to JSON
					data: JSON.stringify(eventData),
					success: function(response) {
						console.log('Event updated successfully');
					},
					error: function() {
						revertFunc();
					}
				});
			},
			dayClick: function(date, jsEvent, view) {
				var clickedDateTime = date.format('YYYY-MM-DD à HH:mm');
				document.getElementById("clickedDateTime").innerHTML = `<i class='fas fa-calendar'></i> ${clickedDateTime}`
				document.getElementById("startDate").value = date.format("YYYY-MM-DD[T]HH:mm:sss");
				$('#myModal').modal('show');
			},

			eventRender: function(event, element) {
				
				document.getElementById("note").addEventListener("click",function(e){
					this.hidden = true;
					this.innerHTML = ""
				})
				element.on('click', function() {
					
					document.getElementById("note").hidden = false;
					if(event.justified){
						document.getElementById("note").className = "m-auto w-50 bg-success mb-3 text-center text-white p-2 rounded"
						document.getElementById("note").innerHTML = `<b>${event.title}</b> le <b>${event.start.format('DD/MM/YYYY à HH:mm')}</b> : <b>Absence justifiée</b>`;
					}
					else{
						document.getElementById("note").className = "m-auto w-50 bg-danger mb-3 text-center text-white p-2 rounded"
						document.getElementById("note").innerHTML =`<b>${event.title}</b> le <b>${event.start.format('DD/MM/YYYY à HH:mm')}</b> : <b>Absence non justifiée</b>`;
					}
				});
			
				
				element.bind('dblclick', function() {
					if (confirm(`Voulez-vous vraiment supprimer cette absence concernant ${event.title}`)) {
						$.ajax({
							url: `/absences/${event.id}`,
							type: 'DELETE',
							success: function(response) {
								$('#teacher-calendar').fullCalendar('removeEvents', event.id);
								let successMessage = document.createElement("div");
								successMessage.className = "alert alert-success text-center"
								successMessage.role = "alert"
								successMessage.innerHTML = `<span>${response}</span>`
								successMessage.addEventListener("click",function(e){
									alert.remove();
								});
								window.scrollTo({ top: 0, behavior: 'smooth' });
								if(document.querySelector(".alert"))
									document.querySelector(".alert").remove();
								document.getElementById("page-header").insertAdjacentElement("afterend", successMessage)
							},
							error: function() {
								let successMessage = document.createElement("div");
								successMessage.className = "alert alert-danger text-center"
								successMessage.role = "alert"
								successMessage.innerHTML = `<span>Une erreur s'est produite ! Veuillez ré-essayer plutard</span>`
								window.scrollTo({ top: 0, behavior: 'smooth' });
								if(document.querySelector(".alert"))
									document.querySelector(".alert").remove();
								document.getElementById("page-header").insertAdjacentElement("afterend", successMessage)
							}
						});
					}
				});
			},


		});
	}

});