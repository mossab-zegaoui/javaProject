const form = document.getElementById('form');
const nom = document.getElementsByName('nom');
const description = document.getElementsByName('description');
const categorie = document.getElementByName('categorie');
const prix = document.getElementByName('prix');
const image = document.getElementByName('image');
const quantite = document.getElementByName('quantite');


form.addEventListener('submit', e => {
    e.preventDefault();

    validateInputs();
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const validateInputs = () => {
    const nomValue = nom.value.trim();
    const descriptionValue = description.value.trim();
    const categorieValue = categorie.value.trim();
    const prixValue = prix.value.trim();
    const imageValue = image.value.trim();
    const quantiteValue = quantite.value.trim();

    if (nomValue === '') {
        setError(nomValue, 'Username is required');
    } else {
        setSuccess(nomValue);
    }
    if (descriptionValue === '') {
        setError(descriptionValue, 'Username is required');
    } else {
        setSuccess(descriptionValue);
    }
    if (categorieValue === '') {
        setError(categorieValue, 'Username is required');
    } else {
        setSuccess(categorieValue);
    }
    if (prixValue === '') {
        setError(prixValue, 'Username is required');
    } else {
        setSuccess(prixValue);
    }
    if (imageValue === '') {
        setError(imageValue, 'Username is required');
    } else {
        setSuccess(imageValue);
    }
    if (quantiteValue === '') {
        setError(quantiteValue, 'Username is required');
    } else {
        setSuccess(quantiteValue);
    }
};