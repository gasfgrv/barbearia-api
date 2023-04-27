ALTER TABLE
  "public"."usuario"
ADD
  CONSTRAINT "usuario_fkey" FOREIGN KEY ("perfil_id") REFERENCES "public"."perfil" ("id");