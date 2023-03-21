ALTER TABLE
  usuario
ADD
  CONSTRAINT usuario_fkey FOREIGN KEY (perfil_id) REFERENCES perfil (id);