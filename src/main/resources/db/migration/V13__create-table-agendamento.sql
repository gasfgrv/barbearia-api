create table "public"."agendamento" (
  "id" uuid not null,
  "cliente" uuid not null,
  "servicos" varchar(255) not null,
  "barbeiro" uuid not null,
  "horario" timestamp not null,
  constraint "agendamento_pkey" primary key ("id")
);