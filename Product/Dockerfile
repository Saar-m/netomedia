FROM nginx:latest as nginx-stage
EXPOSE 80 443
WORKDIR /var/www/client/
COPY . .
RUN mv ./configs/nginx.conf /etc/nginx
RUN chown -R nginx:nginx * && chmod 775 .
CMD ["nginx", "-g", "daemon off;"]