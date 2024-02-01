FROM node:21.6-slim

WORKDIR /web

COPY package.json package-lock.json ./

RUN npm install

RUN npm ci

COPY . .

CMD ["npm", "start"]
