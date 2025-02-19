function extractText() {
    let imageInput = document.getElementById('imageUpload');
    let outputText = document.getElementById('outputText');
    let previewImage = document.getElementById('previewImage');

    if (imageInput.files.length === 0) {
        outputText.innerHTML = "Please upload an image first.";
        return;
    }

    let imageFile = imageInput.files[0];
    let reader = new FileReader();

    reader.onload = function(event) {
        let imgData = event.target.result;
        previewImage.src = imgData;
        previewImage.style.display = "block";

        Tesseract.recognize(
            imgData, 
            'eng', 
            {
                logger: m => console.log(m) // Log progress
            }
        ).then(({ data: { text } }) => {
            outputText.innerText = text || "No text found.";
        }).catch(err => {
            outputText.innerText = "Error recognizing text: " + err;
        });
    };

    reader.readAsDataURL(imageFile);
}
