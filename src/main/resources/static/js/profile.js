var editLink = document.getElementById("edit-link");
var form = document.getElementById("personal-data-form");

window.onload = ()=>{
    editLink.disabled= true;
}
if(form){
	form.addEventListener("change",function(e){
	    editLink.disabled=false;
	})
}
	if(editLink)
	editLink.addEventListener("click", function(event) {
	  // Validate the form fields
	  var firstNameInput = document.getElementById("firstName");
	  var lastNameInput = document.getElementById("lastName");
	  var firstNameArInput = document.getElementById("firstNameAr");
	  var lastNameArInput = document.getElementById("lastNameAr");
	  var birthDateInput = document.getElementById("birthDate");
	  var cneInput = document.getElementById("cne");
	  var cinInput = document.getElementById("cin");
	  var emailInput = document.getElementById("email");
	  var phoneInput = document.getElementById("phone");
		console.log(firstName)
	  // Error message elements
	  var errorMessages = document.getElementsByClassName("error-message");
	  for (var i = 0; i < errorMessages.length; i++) {
	    errorMessages[i].style.display = "none";
	  }
	
	  // Validation flag
	  var isValid = true;
	
	  // Validate First Name
	  if (firstNameInput.value.trim() === "") {
	    displayErrorMessage(firstNameInput, "Please enter your first name.");
	    isValid = false;
	  }
	
	  // Validate Last Name
	  if (lastNameInput.value.trim() === "") {
	    displayErrorMessage(lastNameInput, "Please enter your last name.");
	    isValid = false;
	  }
	
	  // Validate First Name (Arabic)
	  if (firstNameArInput.value.trim() === "") {
	    displayErrorMessage(firstNameArInput, "Please enter your first name in Arabic.");
	    isValid = false;
	  }
	
	  // Validate Last Name (Arabic)
	  if (lastNameArInput.value.trim() === "") {
	    displayErrorMessage(lastNameArInput, "Please enter your last name in Arabic.");
	    isValid = false;
	  }
	
	  // Validate Birth Date
	  if (birthDateInput.value.trim() === "") {
	    displayErrorMessage(birthDateInput, "Please enter your birth date.");
	    isValid = false;
	  }
	
	  // Validate CNE
	  if (cneInput.value.trim() === "") {
	    displayErrorMessage(cneInput, "Please enter your CNE.");
	    isValid = false;
	  }
	
	  // Validate CIN
	  if (cinInput.value.trim() === "") {
	    displayErrorMessage(cinInput, "Please enter your CIN.");
	    isValid = false;
	  }
	
	  // Validate Email
	  var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	  if (emailInput.value.trim() === "") {
	    displayErrorMessage(emailInput, "Please enter your email address.");
	    isValid = false;
	  } else if (!emailRegex.test(emailInput.value.trim())) {
	    displayErrorMessage(emailInput, "Please enter a valid email address.");
	    isValid = false;
	  }
	
	  // Validate Phone
	  var phoneRegex = /^\d{10}$/;
	  if (phoneInput.value.trim() === "") {
	    displayErrorMessage(phoneInput, "Please enter your phone number.");
	    isValid = false;
	  } else if (!phoneRegex.test(phoneInput.value.trim())) {
	    displayErrorMessage(phoneInput, "Please enter a valid 10-digit phone number.");
	    isValid = false;
	  }
	
	  // Prevent form submission if validation fails
	  if (!isValid) {
	    event.preventDefault();
	    return;
	  }
	  editLink.disabled=true;
	  form.submit();
	
	});

function displayErrorMessage(inputElement, errorMessage) {
  var parentElement = inputElement.parentElement;
  var errorMessageElement = parentElement.querySelector(".error-message");
  errorMessageElement.innerText = errorMessage;
  errorMessageElement.style.display = "block";
  errorMessageElement.style.color = "red";
}

//Image
// Get the image container and file input element
const imageContainer = document.getElementById('imageContainer');
const imageUpload = document.getElementById('imageUpload');
const imageForm = document.getElementById("image-form");
// Listen for file upload changes
	if(imageUpload)
	imageUpload.addEventListener('change', function(event) {
	  const file = event.target.files[0];
	
	  // Check if a file is selected
	  if (file) {
	    // Create a new FileReader
	    const reader = new FileReader();
	
	    // Set up the FileReader onload callback
	    reader.onload = function(e) {
	      // Create a new image element
	      const newImage = document.createElement('img');
	      newImage.src = e.target.result;
	      newImage.className = "rounded-circle object-fit-cover";
	      newImage.id = "uploadedImage"
	      newImage.alt = 'Uploaded Image';
	
	      // Remove the previous image (if exists)
	      const previousImage = imageContainer.querySelector('img');
	      if (previousImage) {
	        imageContainer.removeChild(previousImage);
	      }
	      // Append the new image to the container
	      imageContainer.appendChild(newImage);
	      
	      //create a submit button
	      if(!imageForm.contains(document.getElementById("submitImageBtn"))){
			    const submitImageBtn = document.createElement('button');
		      submitImageBtn.type = "submit";
		      submitImageBtn.className = "btn btn-success";
			  submitImageBtn.id = "submitImageBtn";
		      submitImageBtn.innerHTML = "<i class='fas fa-check'></i>";
		      imageForm.appendChild(submitImageBtn);
		  }
		
	    };
	
	    // Read the uploaded file as a data URL
	    reader.readAsDataURL(file);
	  }
	});

//event listener of the success || failed
alert = document.querySelector(".alert");
if(alert)
	alert.addEventListener("click",function(e){
		alert.remove();
	});




