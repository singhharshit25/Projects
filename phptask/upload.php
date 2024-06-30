<?php
// Database configuration
$servername = "localhost";
$username = "root";
$password = " 1234";
$dbname = "file_upload";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Validate form inputs
    $name = filter_var(trim($_POST['name']), FILTER_SANITIZE_STRING);
    $email = filter_var(trim($_POST['email']), FILTER_VALIDATE_EMAIL);
    
    if (!$name || !$email) {
        die("Invalid form inputs");
    }

    // Validate and upload files
    $uploadDir = 'uploads/';
    $allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
    $maxSize = 2 * 1024 * 1024; // 2MB
    $files = $_FILES['attachments'];
    $fileCount = count($files['name']);

    if ($fileCount > 5) {
        die("You can upload a maximum of 5 files.");
    }

    for ($i = 0; $i < $fileCount; $i++) {
        $fileName = basename($files['name'][$i]);
        $fileType = $files['type'][$i];
        $fileTmpName = $files['tmp_name'][$i];
        $fileSize = $files['size'][$i];
        $filePath = $uploadDir . $fileName;

        // Validate file type and size
        if (!in_array($fileType, $allowedTypes)) {
            die("Invalid file type for file $fileName");
        }
        if ($fileSize > $maxSize) {
            die("File $fileName exceeds the maximum size of 2MB");
        }

        // Move the file to the upload directory
        if (move_uploaded_file($fileTmpName, $filePath)) {
            die("Failed to upload file $fileName");
        }

        // Store file info in the database
        $stmt = $conn->prepare("INSERT INTO uploads (name, email, file_name, file_path) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $name, $email, $fileName, $filePath);
        if (!$stmt->execute()) {
            die("Database error: " . $stmt->error);
        }
        $stmt->close();
    }

    echo "Files uploaded and information stored successfully.";
}

$conn->close();
?>
