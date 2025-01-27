function successfullyLogout(){
    alert("You have been successfully logged out");
}

function enforceLength(input) {
    const value = input.value;

    // Rozdzielenie na część całkowitą i ułamkową
    const [integer, decimal] = value.split('.');

    // Maksymalnie 10 cyfr przed przecinkiem
    const maxIntegerLength = 10;
    // Maksymalnie 2 cyfry po przecinku
    const maxDecimalLength = 2;

    let newValue = '';

    if (integer) {
        newValue += integer.slice(0, maxIntegerLength); // Ogranicz część całkowitą
    }

    if (decimal) {
        newValue += '.' + decimal.slice(0, maxDecimalLength); // Ogranicz część ułamkową
    }

    // Aktualizuj wartość inputa
    input.value = newValue;
}

let timeout;

function startTimer() {
    clearTimeout(timeout);
    timeout = setTimeout(() => {
        alert("Zostaniesz wylogowany za chwilę z powodu braku aktywności.");
        window.location.href = '/logout';
    }, 14 * 60 * 1000); // 14 minut (zostawia 1 minutę na reakcję)
}

document.addEventListener('mousemove', startTimer);
document.addEventListener('keypress', startTimer);
startTimer();
