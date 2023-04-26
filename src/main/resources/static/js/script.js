// Get the select element and input field containers
const roleSelect = document.querySelector('#role');
const cinField = document.querySelector('.cin');
const cneField = document.querySelector('.cne');
const gradeField = document.querySelector('.grade');
const birthDateField = document.querySelector('.birthDate');

const fieldsSection = document.querySelector('#fields');
fieldsSection.style.display = "none";

// Add event listener to the select element
roleSelect.addEventListener('change', () => {
  // Get the selected value
  const selectedRole = roleSelect.value;
  
  fieldsSection.style.display = "block";


  // Add input fields based on the selected role
  if (selectedRole === 'Student') {
     gradeField.style.display = "none";
	 cneField.style.display = "block";
	 cinField.style.display = "block";
	 birthDateField.style.display = "block";
	 fieldsSection.style.display = "block";

  } else if (selectedRole === 'Teacher') {
	 gradeField.style.display = "none";
	 cneField.style.display = "none";
	 cinField.style.display = "block";
	 birthDateField.style.display = "none"; 
	 fieldsSection.style.display = "block";
	 
  } else if (selectedRole === 'CadreAdministrator') {
	 gradeField.style.display = "block";
	 cneField.style.display = "none";
	 cinField.style.display = "none";
	 birthDateField.style.display = "none";
	 fieldsSection.style.display = "block";

  }else{
	 gradeField.style.display = "none";
	 cneField.style.display = "none";
	 cinField.style.display = "none";
	 birthDateField.style.display = "none";
	 fieldsSection.style.display = "none";
  }
});
