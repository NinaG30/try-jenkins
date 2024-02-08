# Use the official Node.js 18 image as the base image
FROM node:18

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install the dependencies specified in package.json
RUN npm install

# Copy all files and directories from the current directory to the working directory
COPY . .

# Expose port 3000 for the container
EXPOSE 3000

# Specify the command to run when the container starts
CMD ["node", "src/app.js"]