<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ page import="model.Student" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css"
        integrity="sha512-N415hCJJdJx+1UBfULt+i+ihvOn42V/kOjOpp1UTh4CZ70Hx5bDlKryWaqEKfY/8EYOu/C2MuyaluJryK1Lb5Q=="
        crossorigin="anonymous" />
      <title>Profile</title>
    </head>

    <div class="bg-image" style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png');
            height: 100vh; background-repeat: no-repeat; background-size: cover;
            ">

      <body>

        <jsp:include page="navbar.jsp" />


        <div class="container mt-5 mx-auto w-50">

          <div class="card shadow">
            <div class="card-header text-center bg-white border-0">
              <h1>Profile</h1>
            </div>
            <div class="card-body ">

              <h5 class="card-title">Name</h5>
              <p class="card-text">
                <% Student user=(Student) request.getAttribute("student"); %>
                  <%= user.getFirstName() %>
              </p>
              <h5 class="card-title">Email</h5>
              <p class="card-text">
                <%= user.getEmail() %>
              </p>

              <h5 class="card-title">Phone</h5>
              <p class="card-text">+<%= user.getPhone() %>
              </p>

            </div>
            <div class="card-footer text-center bg-white border-0">
              <a id="edit" class="btn btn-default bg-dark text-white shadow rounded px-5 me-auto" href="#"
                tabindex="-1" aria-disabled="true" onclick="form()">Edit</a>
            </div>
          </div>
        </div>

      </body>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/js/bootstrap.min.js"
      integrity="sha512-mp3VeMpuFKbgxm/XMUU4QQUcJX4AZfV5esgX72JQr7H7zWusV6lLP1S78wZnX2z9dwvywil1VHkHZAqfGOW7Nw=="
      crossorigin="anonymous"></script>

    <script>

      function form() {
        // select the div to be replaced
        var cardBody = document.querySelector('.card-body');

        // create the form
        var form = document.createElement('form');
        // set form attributes
        form.setAttribute('action', 'profileController');
        form.setAttribute('method', 'POST');
        form.classList.add('d-flex', 'flex-column', 'justify-content-center');
        // create form groups
        var formGroup1 = document.createElement('div');
        var formGroup2 = document.createElement('div');
        var formGroup3 = document.createElement('div');
        formGroup1.classList.add('form-group', 'mb-3', 'mx-2');
        formGroup2.classList.add('form-group', 'mb-3', 'mx-2');
        formGroup3.classList.add('form-group', 'mb-3', 'mx-2');

        // submit button
        var btn = document.getElementById('edit');
        let input = document.createElement("input");
        input.setAttribute('type', 'submit');
        input.innerHTML = "Update";
        input.style.backgroundColor = "black";
        input.style.color = "white";
        input.style.border = "none";
        input.style.textAlign = "center";
        input.style.width = "150px";
        input.style.height = "40px";
        input.classList.add('mx-auto', 'rounded', 'shadow');
        var name = '<%=user.getFirstName()%>';
        var email = '<%=user.getEmail()%>';
        var phoneNo = '<%=user.getPhone()%>';
        // create labels
        var nameLabel = document.createElement("LABEL");
        var emailLabel = document.createElement("LABEL");
        var phoneLabel = document.createElement("LABEL");
        nameLabel.innerHTML = "Name: ";
        emailLabel.innerHTML = "Email: ";
        phoneLabel.innerHTML = "Phone: ";
        //br
        var br = document.createElement("br");
        // create the name input
        var nameInput = document.createElement('input');
        nameInput.setAttribute('type', 'text');
        nameInput.setAttribute('name', 'name');
        nameInput.setAttribute('placeholder', name);
        nameInput.setAttribute('value', name);
        nameInput.classList.add('form-control');
        formGroup1.appendChild(nameLabel);
        formGroup1.appendChild(nameInput);
        formGroup1.appendChild(br);
        form.appendChild(formGroup1);
        // create the email input
        var emailInput = document.createElement('input');
        emailInput.setAttribute('type', 'email');
        emailInput.setAttribute('name', 'email');
        emailInput.setAttribute('placeholder', email);
        emailInput.setAttribute('value', email);
        emailInput.classList.add('form-control');
        formGroup2.appendChild(emailLabel);
        formGroup2.appendChild(emailInput);
        formGroup2.appendChild(br);
        form.appendChild(formGroup2);


 
                
        // create the phone input
        var phoneInput = document.createElement('input');
        phoneInput.setAttribute('type', 'tel');
        phoneInput.setAttribute('name', 'phone');
        phoneInput.setAttribute('placeholder', phoneNo);
        phoneInput.setAttribute('value', phoneNo);
        phoneInput.classList.add('form-control');
        formGroup3.appendChild(phoneLabel);
        formGroup3.appendChild(phoneInput);
        formGroup3.appendChild(br);
        form.appendChild(formGroup3);
        btn.parentNode.replaceChild(input, btn);
		form.appendChild(input);

	   
	        
	        
        // replace the div with the form
        cardBody.parentNode.replaceChild(form, cardBody);


      }

    </script>


    </html>