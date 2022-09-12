//Este arquivo não tem relação com este projeto mas foi usado em aula como exemplo de async em outras plataformas.
const exec = ()=>{
  setTimeout(()=>console.log("Executei o primeiro!"),10000);
}
const exec2 = ()=>{
  setTimeout(()=>console.log("Executei o segundo!"),3000);
}

exec();
exec2();
console.log("Este é o terceiro!")