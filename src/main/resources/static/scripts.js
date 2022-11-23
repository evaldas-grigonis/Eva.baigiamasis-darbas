const API_BASE_URL = "http://localhost:8080";

const submitPersonForm = async (name, lastName, email, dateOfBirth) => {
  await fetch(`${API_BASE_URL}/RegistrationFormsOfPersons`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ name, lastName, email, dateOfBirth }),
  });

  await renderPersonRegistrationTable();
};

const getAllPersonForms = async () => {
  const response = await fetch(`${API_BASE_URL}/RegistrationFormsOfPersons`);
  const registrationFormsOfPersons = await response.json();
  return registrationFormsOfPersons;
};

const handlePersonRegistrationFormSubmit = () => {
  const form = document.getElementById("personRegistrationForm");
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    submitPersonForm(
      form.name.value,
      form.lastName.value,
      form.email.value,
      form.dateOfBirth.value
    );
  });
};

const renderPersonRegistrationTable = async () => {
  const registrationFormsOfPersons = await getAllPersonForms();
  if (document.getElementById("personRegistrationTable")) {
    document.getElementById("personRegistrationTable").remove();
  }
  const table = document.createElement("table");
  table.id = "personRegistrationTable";
  table.className = "table"
  renderPersonRegistrationTableHeaders(table);
  registrationFormsOfPersons.forEach((person) => {
    renderPersonRegistrationTableRow(table, person);
  });
  document.getElementById("personRegistrationContainer").appendChild(table);
};

const renderTableCell = (innerText, className) => {
  const td = document.createElement("td");
  if (innerText) {
    td.innerText = innerText;
  }
  if (className) {
    td.className = className;
  }
  return td;
};
const renderTableHeader = (innerText) => {
  const th = document.createElement("th");
  th.innerText = innerText;
  return th;
};
const renderActionButtons = (actionsCell, id) => {
  const editButton = document.createElement("button");
  editButton.innerText = "Edit";
  editButton.className = "editButton";
  editButton.addEventListener("click", () => {
    handleEdit(id);
  });

  const deleteButton = document.createElement("button");
  deleteButton.innerText = "Delete";
  deleteButton.className = "deleteButton";
  deleteButton.addEventListener("click", () => {
    handleDelete(id);
  });

  actionsCell.append(editButton, deleteButton);
};

const handleEdit = async (id) => {
  const tr = document.getElementById(`person-${id}`);

  const nameCell = tr.querySelector(".nameCell");
  const nameInput = document.createElement("input");
  nameInput.type = "text";
  nameInput.value = nameCell.innerText;
  nameCell.innerText = "";
  nameCell.appendChild(nameInput);

  const lastNameCell = tr.querySelector(".lastNameCell");
  const lastNameInput = document.createElement("input");
  lastNameInput.type = "text";
  lastNameInput.value = lastNameCell.innerText;
  lastNameCell.innerText = "";
  lastNameCell.appendChild(lastNameInput);

  const emailCell = tr.querySelector(".emailCell");
  const emailInput = document.createElement("input");
  emailInput.type = "text";
  emailInput.value = emailCell.innerText;
  emailCell.innerText = "";
  emailCell.appendChild(emailInput);

  const dateOfBirthCell = tr.querySelector(".dateOfBirthCell");
  const dateOfBirthInput = document.createElement("input");
  dateOfBirthInput.type = "text";
  dateOfBirthInput.value = dateOfBirthCell.innerText;
  dateOfBirthCell.innerText = "";
  dateOfBirthCell.appendChild(dateOfBirthInput);

  const actionsCell = tr.querySelector(".actionsCell");
  actionsCell.querySelector(".editButton").remove();
  actionsCell.querySelector(".deleteButton").remove();

  const saveButton = document.createElement("button");
  saveButton.innerText = "Save";
  saveButton.className = "saveButton";
  saveButton.addEventListener("click", () => {
    handleSave(id);
  });

  const cancelButton = document.createElement("button");
  cancelButton.innerText = "Cancel";
  cancelButton.className = "cancelButton";
  cancelButton.addEventListener("click", () => {
    window.location.reload();
  });

  actionsCell.append(saveButton, cancelButton);
};

const handleSave = async (id) => {
  const tr = document.getElementById(`person-${id}`);

  const nameCell = tr.querySelector(".nameCell");
  const nameInput = nameCell.querySelector("input");

  const lastNameCell = tr.querySelector(".lastNameCell");
  const lastNameInput = lastNameCell.querySelector("input");

  const emailCell = tr.querySelector(".emailCell");
  const emailInput = emailCell.querySelector("input");

  const dateOfBirthCell = tr.querySelector(".dateOfBirthCell");
  const dateOfBirthInput = dateOfBirthCell.querySelector("input");

  await fetch(`${API_BASE_URL}/RegistrationFormsOfPersons/${id}`, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      name: nameInput.value,
      lastName: lastNameInput.value,
      email: emailInput.value,
      dateOfBirth: dateOfBirthInput.value
    }),
  });
  await renderPersonRegistrationTable();
};

const handleDelete = async (id) => {
  await fetch(`${API_BASE_URL}/RegistrationFormsOfPersons/${id}`, {
    method: "DELETE",
  });
  await renderPersonRegistrationTable();
};

const renderPersonRegistrationTableRow = (table, person) => {
  const tr = document.createElement("tr");
  tr.id = `person-${person.id}`;
  const nameCell = renderTableCell(person.name, "nameCell");
  const lastNameCell = renderTableCell(person.lastName, "lastNameCell");
  const emailCell = renderTableCell(person.email, "emailCell");
  const dateOfBirthCell = renderTableCell(person.dateOfBirth,"dateOfBirthCell");
  const actionsCell = renderTableCell(undefined, "actionsCell");
  renderActionButtons(actionsCell, person.id);

  tr.append(nameCell, lastNameCell, emailCell, dateOfBirthCell, actionsCell);
  table.appendChild(tr);
};

const renderPersonRegistrationTableHeaders = (table) => {
  const tr = document.createElement("tr");

  const nameTh = renderTableHeader("name");
  const lastNameTh = renderTableHeader("lastName");
  const emailTh = renderTableHeader("email");
  const dateOfBirthTh = renderTableHeader("dateOfBirth");
  const actionsTh = renderTableHeader("Actions");

  tr.append(nameTh, lastNameTh, emailTh, dateOfBirthTh, actionsTh);
  table.appendChild(tr);
};

(async () => {
  await renderPersonRegistrationTable();
  await handlePersonRegistrationFormSubmit();
})();
