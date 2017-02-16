function goToUser(){
            var loginVar = document.getElementById('name').value;
            var passwordVar = document.getElementById('password').value;
            var info = {};
            info["login"] = loginVar;
            info["passwordVar"] = passwordVar;
            /*
            var xhr = new XMLHttpRequest();
            xhr.open("POST","/user", true);
            xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            xhr.send(data);
            */
            $.ajax({
              type: 'POST',
              url: 'http://localhost:8080/user',
              contentType: 'application/json',
              data: JSON.stringify(info),
            });
}