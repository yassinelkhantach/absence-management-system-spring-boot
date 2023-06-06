function deleteTeacher(teacherId) {
	if(confirm("Voulez-vous vraiment supprimer cet professeur ?")){
    let teacherToDelete = document.getElementById("teacher-to-delete");
    teacherToDelete.value = teacherId;
    if (teacherToDelete.value != null)
      return true;
    else
      return false;
  }
  return false;
}

function restoreTeacher(teacherId) {
  if (confirm("Voulez-vous vraiment récupérer cet professeur ?")) {
    let teacherToRestore = document.getElementById("teacher-to-restore");
    teacherToRestore.value = teacherId;
    if (teacherToRestore.value != null)
      return true;
    else
      return false;
  }
  return false;
}

function validateForm(event) {
    event.preventDefault();

    // Clear previous error messages
    const errorMessages = document.querySelectorAll(".error-message");
    errorMessages.forEach((element) => {
		element.textContent = ""
		element.style.color = "red";
	});

    // Get form input values
    const firstNameFr = document.getElementById("firstNameFr").value;
    const lastNameFr = document.getElementById("lastNameFr").value;
    const firstNameAr = document.getElementById("firstNameAr").value;
    const lastNameAr = document.getElementById("lastNameAr").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const cin = document.getElementById("cin").value;
	let i = 0;
    // Check if fields are empty
    if (firstNameFr === "") {
      document.getElementById("firstNameFrError").textContent = "Le prénom en français est obligatoire.";
      i++;
    }

    if (lastNameFr === "") {
      document.getElementById("lastNameFrError").textContent = "Le nom en français est obligatoire.";
	  i++;
    }

    if (firstNameAr === "") {
      document.getElementById("firstNameArError").textContent = "Le prénom en arabe est obligatoire.";
      i++;
    }

    if (lastNameAr === "") {
      document.getElementById("lastNameArError").textContent = "Le nom en arabe est obligatoire.";
      i++;
    }

    if (email === "") {
      document.getElementById("emailError").textContent = "Email est obligatoire.";
      i++;
    }

    if (phone === "") {
      document.getElementById("phoneError").textContent = "Le numéro de téléphone est obligatoire.";
      i++;
    }

    if (cin === "") {
      document.getElementById("cinError").textContent = "Le CIN est obligatoire.";
      i++;
    }

	if(i == 0){
		document.getElementById("edit-teacher-button").disabled = true;
		event.target.submit();
	}else{
		window.scrollTo({top: 0,behavior: "smooth"});
	}
 }
 
 function toggleButton(){
	 document.getElementById("edit-teacher-button").disabled = false;
 }
 
 //event listener of the success || failed
alert = document.querySelector(".alert");
if(alert){
	alert.addEventListener("click",function(e){
		alert.remove();
	});
}

window.onload = () => {
	if(document.getElementById("edit-teacher-button"))
		document.getElementById("edit-teacher-button").disabled = true;
}
	

