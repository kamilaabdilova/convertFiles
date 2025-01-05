# Convert Files API

This is a REST API that allows you to convert multiple image files (JPEG, PNG) to the WebP format. The API accepts files in a `multipart/form-data` format and returns the converted images in a Base64-encoded WebP format.

## Features

- Converts JPEG and PNG images to WebP format.
- Allows bulk file upload and conversion.
- Supports adjustable image quality (from 10 to 100).
- Handles multiple files in a single request.
- Returns Base64-encoded WebP images.

## Endpoints

### POST /convertFile/multiple

Convert multiple image files to WebP format.

#### Request

**URL**: `/convertFile/multiple`

**Method**: `POST`

**Headers**:
- `Content-Type: multipart/form-data`

**Parameters**:
- `files` (required): A list of image files to be converted.
- `quality` (optional, default is 80): The quality of the converted WebP images (from 10 to 100).