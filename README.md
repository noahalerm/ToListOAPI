# ToListO 
[web a Gitlab](https://gitlab.com/MiquelOrtizGraupera/proyecto_m-13.git)

[web a Heroku](https://tolistoapi.herokuapp.com/)

## Introduction  
As part of the DAM M13 project, we want to create a web interface that allows us to manage one or more to-do lists, the main actions to perform with the items in the list will be: mark as done, add a to-do, delete a task (done or pending).


### Company and product naming  
Our fictional company’s name is _EVENSTON_, and the name of our product is _ToListO_, since it’s based on making to-do lists.


## Appearance and design
Our logo has an integrated animation to attract the attention of different users and to make it more pleasing to see.

The color scheme chosen for our website is:

#5d8fde → Background

#81a7e3 → Primary color light

#e4e7ed → Text

#ac75eb → Secondary color dark

#c19aed → Secondary color light


## Website Navigation (Endpoint Guide)
In this section there are the endpoints to every action one can perform on the website through our API.

### Lists:
* **GET** → *"website URL"*/lists
* **POST** → *"website URL"*/lists
* **DELETE** → *"website URL"*/lists/{list id}
* **PUT** → *"website URL"*/lists/{list id}

### Tasks / items:

* **GET (all items on a list)** → *"website URL"*/lists/{list id}/tasks
* **GET (specific item)** → *"website URL"*/lists/{list id}/tasks/{task id}
* **POST** → *"website URL"*/lists/{list id}/tasks
* **DELETE** → *"website URL"*/lists/{list id}/tasks/{task id}
* **PUT** → *"website URL"*/lists/{list id}/tasks/{task id}


## Webgraphy and inspiration
In this section of the documentation we have listed several of the websites we used as inspiration, to create the graphic elements of our product or to research how to implement different functionalities. 

Logo: https://www.canva.com/design/play?category=tACZCvjI6mE&locale=es-419 

Color palette: https://paletadecolores.online/crear-paleta/ 

Inspiration: https://todoist.com/ 
