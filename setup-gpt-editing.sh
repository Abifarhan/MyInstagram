#!/bin/bash
# GPT File Editing Setup Script for Android Projects

echo "=== GPT File Editing Setup for MyInstagram Project ==="

# Method 1: VS Code with GPT Extensions
echo "1. Setting up VS Code with GPT extensions..."
echo "   - Install VS Code if not already installed"
echo "   - Install extensions: ChatGPT - Genie AI, GitHub Copilot"
echo "   - Configure OpenAI API key in extension settings"

# Method 2: Cursor IDE Setup
echo "2. Setting up Cursor IDE..."
echo "   - Download from: https://cursor.sh/"
echo "   - Configure GPT-4 API key"
echo "   - Import your project folder: D:\\MyInstagram"

# Method 3: Android Studio AI Assistant
echo "3. Setting up Android Studio AI Assistant..."
echo "   - Go to File → Settings → Plugins"
echo "   - Install 'AI Assistant' plugin"
echo "   - Configure multiple AI providers (OpenAI + Anthropic)"

# Method 4: API Integration
echo "4. Creating API integration script..."

cat > "gpt_file_editor.py" << 'EOF'
import openai
import os
import sys

class GPTFileEditor:
    def __init__(self, api_key):
        openai.api_key = api_key
    
    def edit_file(self, file_path, instructions):
        """Edit a file using GPT"""
        try:
            # Read current file content
            with open(file_path, 'r', encoding='utf-8') as f:
                current_content = f.read()
            
            # Create GPT prompt
            prompt = f"""
            Edit the following file according to these instructions:
            {instructions}
            
            Current file content:
            ```
            {current_content}
            ```
            
            Please provide the complete updated file content:
            """
            
            # Get GPT response
            response = openai.ChatCompletion.create(
                model="gpt-4",
                messages=[{"role": "user", "content": prompt}],
                max_tokens=4000
            )
            
            # Extract code from response
            updated_content = response.choices[0].message.content
            
            # Remove code block markers if present
            if updated_content.startswith('```'):
                lines = updated_content.split('\n')
                updated_content = '\n'.join(lines[1:-1])
            
            # Write updated content back to file
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(updated_content)
            
            print(f"✅ Successfully updated {file_path}")
            return True
            
        except Exception as e:
            print(f"❌ Error editing {file_path}: {str(e)}")
            return False
    
    def batch_edit(self, file_instructions):
        """Edit multiple files in batch"""
        for file_path, instructions in file_instructions.items():
            self.edit_file(file_path, instructions)

# Usage example:
if __name__ == "__main__":
    if len(sys.argv) < 4:
        print("Usage: python gpt_file_editor.py <API_KEY> <FILE_PATH> <INSTRUCTIONS>")
        sys.exit(1)
    
    api_key = sys.argv[1]
    file_path = sys.argv[2]
    instructions = sys.argv[3]
    
    editor = GPTFileEditor(api_key)
    editor.edit_file(file_path, instructions)
EOF

echo "   ✅ Created gpt_file_editor.py"

# Method 5: PowerShell automation
echo "5. Creating PowerShell automation..."

cat > "gpt-edit.ps1" << 'EOF'
param(
    [string]$FilePath,
    [string]$Instructions,
    [string]$ApiKey = $env:OPENAI_API_KEY
)

if (-not $ApiKey) {
    Write-Error "Please set OPENAI_API_KEY environment variable or pass -ApiKey parameter"
    exit 1
}

# Use the Python script to edit files
python "gpt_file_editor.py" $ApiKey $FilePath $Instructions
EOF

echo "   ✅ Created gpt-edit.ps1"

# Create configuration files
echo "6. Creating configuration files..."

cat > "gpt-config.json" << 'EOF'
{
    "openai_api_key": "YOUR_API_KEY_HERE",
    "model": "gpt-4",
    "max_tokens": 4000,
    "project_path": "D:\\MyInstagram",
    "common_instructions": {
        "android_kotlin": "Follow Android Kotlin best practices, use Jetpack Compose, implement proper error handling",
        "modular_architecture": "Maintain clean architecture with separate modules for features, core, and data layers",
        "hilt_injection": "Use Hilt for dependency injection with @HiltViewModel for ViewModels"
    }
}
EOF

echo "   ✅ Created gpt-config.json"

echo ""
echo "=== Setup Complete! ==="
echo ""
echo "Next steps:"
echo "1. Choose your preferred method from above"
echo "2. For API integration: Set your OpenAI API key in gpt-config.json"
echo "3. Test with a simple file edit"
echo ""
echo "Example usage:"
echo "   python gpt_file_editor.py YOUR_API_KEY 'feature/profile/src/main/.../ProfileScreen.kt' 'Add a loading indicator'"
echo ""

# Make scripts executable
chmod +x gpt_file_editor.py 2>/dev/null || true
chmod +x gpt-edit.ps1 2>/dev/null || true

echo "All scripts are ready to use!"