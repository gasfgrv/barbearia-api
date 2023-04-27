ALTER TABLE
  "public"."cliente"
ADD
  CONSTRAINT "cliente_fkey" FOREIGN KEY ("login") REFERENCES "public"."usuario" ("login");