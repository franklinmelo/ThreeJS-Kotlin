var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera(
75,
window.innerWidth / window.innerHeight,
0.1,
1000
);

var renderer = new THREE.WebGLRenderer({ alpha: true }  );
renderer.setSize( window.innerWidth, window.innerHeight );
 renderer.setClearColor( 0xffffff, 0 );
document.body.appendChild( renderer.domElement );

var loader = new THREE.FontLoader();

loader.load( 'https://cdn.rawgit.com/mrdoob/three.js/master/examples/fonts/helvetiker_regular.typeface.json', function ( font ) {

      var geometry = new THREE.TextGeometry( 'Hello three.js!', {
                                            font: font,
                                            size: 4,
                                            height: 0.3,
                                            curveSegments: 10,
                                            bevelSize: 0,
                                            bevelOffset: 0,
                                            bevelSegments: 0
                                            } );
      var material = new THREE.MeshBasicMaterial({ color: 0xff0000 });
      var cube = new THREE.Mesh(geometry, material);
      scene.add(cube);

      camera.position.z = 30;
      camera.position.x = 15;
      camera.position.y = 1;
      renderer.render(scene, camera);

} );